package rs.gov.mduls.einicijative.niapi.api.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.gov.mduls.einicijative.niapi.api.interfaces.InicijatorApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.Generated;
import rs.gov.mduls.einicijative.niapi.api.model.*;
import rs.gov.mduls.einicijative.niapi.clients.euprava.EupravaRESTClient;
import rs.gov.mduls.einicijative.niapi.clients.euprava.dto.DetaljiOGradjaninu;
import rs.gov.mduls.einicijative.niapi.db.NiDatabaseApi;
import rs.gov.mduls.einicijative.niapi.utils.Consts;
import rs.gov.mduls.einicijative.niapi.utils.Nadgledanje;
import rs.gov.mduls.einicijative.niapi.utils.NadzorniTrag;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.ni.base-path:/niapi}")
public class InicijatorApiController extends ApiControllerBase implements InicijatorApi {

    @Autowired
    private NiDatabaseApi dbApi;

    @Autowired
    private EupravaRESTClient eupravaServis;

    private static final Logger logger = LoggerFactory.getLogger(InicijatorApiController.class);

    private final NativeWebRequest request;

    @Autowired
    public InicijatorApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    private void pripremiJwt() throws ParseException {
        super.pripremiJwt(request.getHeader(Consts.HTTP_HEADER_AUTHORIZATION),logger);
    }

    private void incNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            NiDatabaseApi.IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine,
            String imePrezime
    ) {
        // prvi metod sa novom sesijom šalje događaj prijave u nadzorni dnevnik
        dbApi.incNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, idKorisnika, idPola, godinaRodjenja, idOpstine, imePrezime);
        NadzorniTrag.prijava("inc", idKorisnika.toString());
    }

    private void incDajUpisiSesiju() throws ParseException, InterruptedException, URISyntaxException, IOException {
        pripremiJwt();
        NiDatabaseApi.Sesija sesija = dbApi.dajSesijuPoHash(jwtHash);
        if (!sesija.prisutna()) {
            DetaljiOGradjaninu detalji = eupravaServis.dajInicijatora(jwt);
            incNovaSesija(
                    jwtHash,
                    jwt,
                    parsedJwt.getJWTClaimsSet().getExpirationTime(),
                    detalji.getIdKorisnika(),
                    NiDatabaseApi.IdPolaEnum.fromValue(detalji.getIdPola().getValue()),
                    detalji.getGodinaRodjenja(),
                    detalji.getIdOpstine(),
                    detalji.getImePrezime()
            );
        }
    }

    /*******************************************************************************************************************
     *   Implementacije interfejsa odavde na dole
     */

    @Override
    public ResponseEntity<InicijatorProfilOdgovor> inicijatorProfilGet(
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incProfil";
        final String porukaOGresci = "Читање профила није успело";
        try {
            incDajUpisiSesiju();
            NiDatabaseApi.IncProfil profil =
                    dbApi.incDajProfil(jwtHash);
            InicijatorProfilOdgovor odgovor = new InicijatorProfilOdgovor();
            odgovor.setGodinaRodjenja(profil.godinaRodjenja());
            odgovor.setNazivOpstine(profil.nazivOpstine());
            if (profil.idPola() == NiDatabaseApi.IdPolaEnum.M) {
                odgovor.setIdPola(InicijatorProfilOdgovor.IdPolaEnum.M);
            } else {
                odgovor.setIdPola(InicijatorProfilOdgovor.IdPolaEnum.Z);
            }
            odgovor.setEmailAdresa(profil.emailAdresa());
            odgovor.setImePrezime(profil.imePrezime());
            odgovor.setBiografija(profil.biorafija());
            ResponseEntity<InicijatorProfilOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorBiografijaPut(
            InicijatorProfilBiografijaZahtev inicijatorProfilBiografijaZahtev
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incPodesiBiografiju";
        final String porukaOGresci = "Упис биографије није успео";
        try {
            incDajUpisiSesiju();
            dbApi.incPodesiBiografiju(jwtHash, inicijatorProfilBiografijaZahtev.getBiografija());
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Упис биографије је успео");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorEmailPut(
            InicijatorProfilEmailZahtev inicijatorProfilEmailZahtev
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incPodesiEmail";
        final String porukaOGresci = "Упис Е-маил адресе није успео";
        try {
            incDajUpisiSesiju();
            dbApi.incPodesiEmail(jwtHash, inicijatorProfilEmailZahtev.getEmailAdresa());
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Упис Е-маил адресе је успео");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<InicijatorListaInicijativaOdgovor> inicijatorInicijativaPofaziobradeIdFazeObradeGet(
            String idFazeObrade
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incListaPoFaziObrade";
        final String porukaOGresci = "Проблем са добављањем листе по фази обраде";
        try {
            incDajUpisiSesiju();
            String potpisiJSON =
                    dbApi.incListaPoFaziObrade(jwtHash,idFazeObrade);
            InicijatorListaInicijativaOdgovor odgovor = new InicijatorListaInicijativaOdgovor();
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                List<OsnovnoOInicijativi> inicijative = mapper.readValue(potpisiJSON, new TypeReference<List<OsnovnoOInicijativi>>(){});
                odgovor.setInicijativeZaFazuObrade(inicijative);
            }
            ResponseEntity<InicijatorListaInicijativaOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<InicijatorInicijativaOdgovor> inicijatorInicijativaIdInicijativeGet(
            Long idInicijative
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incDetaljiInicijative";
        final String porukaOGresci = "Проблем са добављањем детаља иницијативе";
        try {
            incDajUpisiSesiju();
            String potpisiJSON =
                    dbApi.incDetaljiInicijative(jwtHash,idInicijative.intValue());
            InicijatorInicijativaOdgovor odgovor = new InicijatorInicijativaOdgovor();
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                InicijativaDetalji inicijativa = mapper.readValue(potpisiJSON, new TypeReference<InicijativaDetalji>(){});
                odgovor.setDetaljiInicijative(inicijativa);
            }
            ResponseEntity<InicijatorInicijativaOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<InicijatorNovaInicijativaOdgovor> inicijatorInicijativaPost(
            InicijatorInicijativaZahtev inicijatorInicijativaZahtev
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incDodajInicijativu";
        final String porukaOGresci = "Упис иницијативе није успео";
        try {
            incDajUpisiSesiju();
            Integer idInicijative =
                    dbApi.incDodajInicijativu(
                            jwtHash,
                            inicijatorInicijativaZahtev.getTipInicijative(),
                            inicijatorInicijativaZahtev.getNazivInicijative(),
                            inicijatorInicijativaZahtev.getTekstInicijative(),
                            inicijatorInicijativaZahtev.getEmailZaKontakt(),
                            inicijatorInicijativaZahtev.getNivoVlasti(),
                            inicijatorInicijativaZahtev.getJedinicaVlasti()
                            );
            InicijatorNovaInicijativaOdgovor odgovor = new InicijatorNovaInicijativaOdgovor();
            String potpisiJSON = dbApi.incDetaljiInicijative(jwtHash,idInicijative);
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                InicijativaDetalji inicijativa = mapper.readValue(potpisiJSON, new TypeReference<InicijativaDetalji>(){});
                odgovor.setDetaljiInicijative(inicijativa);
            }
            ResponseEntity<InicijatorNovaInicijativaOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativePut(
            Long idInicijative,
            InicijatorInicijativaZahtev inicijatorInicijativaZahtev
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incIzmeniInicijativu";
        final String porukaOGresci = "Измена иницијативе није успела";
        try {
            incDajUpisiSesiju();
            dbApi.incIzmeniInicijativu(
                    jwtHash,
                    idInicijative.intValue(),
                    inicijatorInicijativaZahtev.getTipInicijative(),
                    inicijatorInicijativaZahtev.getNazivInicijative(),
                    inicijatorInicijativaZahtev.getTekstInicijative(),
                    inicijatorInicijativaZahtev.getEmailZaKontakt(),
                    inicijatorInicijativaZahtev.getNivoVlasti(),
                    inicijatorInicijativaZahtev.getJedinicaVlasti()
            );
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Упис измена иницијативе је успео");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativePrilogIdPrilogaDelete(
            Long idInicijative,
            Long idPriloga
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incObrisiPrilogInicijative";
        final String porukaOGresci = "Брисање прилога иницијативе није успело";
        try {
            incDajUpisiSesiju();
            dbApi.incObrisiPrilogInicijative(jwtHash, idPriloga.intValue());
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Брисање прилога иницијативе је успео");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<InicijatorPrilogInicijativeOdgovor> inicijatorInicijativaIdInicijativePrilogPost(
            Long idInicijative,
            InicijatorPrilogInicijativeZahtev inicijatorPrilogInicijativeZahtev
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incDodajPrilogInicijative";
        final String porukaOGresci = "Упис прилога иницијативе није успео";
        try {
            incDajUpisiSesiju();
            String urlPriloga = null;
            // TODO: snimi na fajl sistem i formiraj URL za pristup sa fajlsistema
            dbApi.incDodajPrilogInicijative(
                    jwtHash,
                    idInicijative.intValue(),
                    urlPriloga,
                    inicijatorPrilogInicijativeZahtev.getNazivPriloga(),
                    inicijatorPrilogInicijativeZahtev.getSortiranje()
            );
            InicijatorPrilogInicijativeOdgovor odgovor = new InicijatorPrilogInicijativeOdgovor();
            String potpisiJSON = dbApi.incDetaljiInicijative(jwtHash,idInicijative.intValue());
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                InicijativaDetalji inicijativa = mapper.readValue(potpisiJSON, new TypeReference<InicijativaDetalji>(){});
                odgovor.setDetaljiInicijative(inicijativa);
            }
            ResponseEntity<InicijatorPrilogInicijativeOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<InicijatorPrilogInicijativeOdgovor> inicijatorInicijativaIdInicijativePrilogIdPrilogaPut(
            Long idInicijative,
            Long idPriloga,
            InicijatorPrilogInicijativeZahtev inicijatorPrilogInicijativeZahtev
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "incIzmeniPrilogInicijative";
        final String porukaOGresci = "Упис измене прилога иницијативе није успео";
        try {
            incDajUpisiSesiju();
            String urlPriloga = null;
            // TODO: snimi na fajl sistem i formiraj URL za pristup sa fajlsistema
            dbApi.incIzmeniPrilogInicijative(
                    jwtHash,
                    idPriloga.intValue(),
                    urlPriloga,
                    inicijatorPrilogInicijativeZahtev.getNazivPriloga(),
                    inicijatorPrilogInicijativeZahtev.getSortiranje()
            );
            InicijatorPrilogInicijativeOdgovor odgovor = new InicijatorPrilogInicijativeOdgovor();
            String potpisiJSON = dbApi.incDetaljiInicijative(jwtHash,idInicijative.intValue());
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                InicijativaDetalji inicijativa = mapper.readValue(potpisiJSON, new TypeReference<InicijativaDetalji>(){});
                odgovor.setDetaljiInicijative(inicijativa);
            }
            ResponseEntity<InicijatorPrilogInicijativeOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativePozivnicaDelete(
            Long idInicijative
    ) {
        return null;
    }

    @Override
    public ResponseEntity<InicijatorPozivnicaOdgovor> inicijatorInicijativaIdInicijativePozivnicaPost(
            Long idInicijative
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativePozivnicaPut(
            Long idInicijative,
            InicijatorPozivnicaZahtev inicijatorPozivnicaZahtev
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativeNapustiPut(
            Long idInicijative
    ) {
        return null;
    }


    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativePodnesiPut(
            Long idInicijative
    ) {
        return null;
    }


    @Override
    public ResponseEntity<Uspeh> inicijatorInicijativaIdInicijativePokreniPut(
            Long idInicijative
    ) {
        return null;
    }

}
