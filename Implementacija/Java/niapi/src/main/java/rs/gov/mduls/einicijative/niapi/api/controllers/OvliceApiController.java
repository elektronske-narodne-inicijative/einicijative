package rs.gov.mduls.einicijative.niapi.api.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import rs.gov.mduls.einicijative.niapi.api.interfaces.OvliceApi;

import rs.gov.mduls.einicijative.niapi.api.model.*;
import rs.gov.mduls.einicijative.niapi.clients.euprava.dto.DetaljiOOvlascenomLicu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.Generated;
import rs.gov.mduls.einicijative.niapi.clients.euprava.EupravaRESTClient;
import rs.gov.mduls.einicijative.niapi.db.NiDatabaseApi;
import rs.gov.mduls.einicijative.niapi.utils.Consts;
import rs.gov.mduls.einicijative.niapi.utils.Nadgledanje;
import rs.gov.mduls.einicijative.niapi.utils.NadzorniTrag;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.ni.base-path:/niapi}")
public class OvliceApiController extends ApiControllerBase implements OvliceApi {

    @Autowired
    private NiDatabaseApi dbApi;

    @Autowired
    private EupravaRESTClient eupravaServis;

    private static final Logger logger = LoggerFactory.getLogger(OvliceApiController.class);

    private final NativeWebRequest request;

    @Autowired
    public OvliceApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    private void pripremiJwt() throws ParseException {
        super.pripremiJwt(request.getHeader(Consts.HTTP_HEADER_AUTHORIZATION),logger);
    }

    private void ovlNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            String idNivoaVlasti,
            String idOpstine,
            String idPokrajine,
            String imePrezime,
            String emailAdresa
    ) {
        // prvi metod sa novom sesijom šalje događaj prijave u nadzorni dnevnik
        dbApi.ovlNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, idKorisnika, idNivoaVlasti, idOpstine, idPokrajine, imePrezime, emailAdresa);
        NadzorniTrag.prijava("ovl", idKorisnika.toString());
    }

    private void ovlDajUpisiSesiju() throws ParseException, InterruptedException, URISyntaxException, IOException {
        pripremiJwt();
        NiDatabaseApi.Sesija sesija = dbApi.dajSesijuPoHash(jwtHash);
        if (!sesija.prisutna()) {
            DetaljiOOvlascenomLicu detalji = eupravaServis.dajOvlascenoLice(jwt);
            ovlNovaSesija(
                    jwtHash,
                    jwt,
                    parsedJwt.getJWTClaimsSet().getExpirationTime(),
                    detalji.getIdKorisnika(),
                    detalji.getIdNivoaUprave().getValue(),
                    detalji.getIdOpstine(),
                    null, // FIXME: idPokrajine
                    detalji.getImePrezime(),
                    detalji.getEmailAdresa()
            );
        }
    }

    /*******************************************************************************************************************
     *   Implementacije interfejsa odavde na dole
     */

    @Override
    public ResponseEntity<OvliceInicijativaOdgovor> ovliceInicijativaIdInicijativeGet(
            Long idInicijative
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlDetaljiInicijative";
        final String porukaOGresci = "Проблем са приступом детаљима иницијативе";
        try {
            ovlDajUpisiSesiju();
            String inicijativaJSON =
                    dbApi.ovlDetaljiInicijative(jwtHash, idInicijative.intValue());
            OvliceInicijativaOdgovor odgovor = new OvliceInicijativaOdgovor();
            if (inicijativaJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                InicijativaDetalji detalji = mapper.readValue(inicijativaJSON, new TypeReference<InicijativaDetalji>(){});
                odgovor.setDetaljiInicijative(detalji);
            }
            ResponseEntity<OvliceInicijativaOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> ovliceInicijativaIdInicijativeOdbacenaPut(
            Long idInicijative,
            ObrazlozenjeSaSednice obrazlozenjeSaSednice
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlRegistrujOdbacenuInicijativu";
        final String porukaOGresci = "Проблем са завођењем одбацивања иницијативе";
        try {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            ovlDajUpisiSesiju();
            dbApi.ovlRegistrujOdbacenuInicijativu(
                    jwtHash,
                    idInicijative.intValue(),
                    Date.from(obrazlozenjeSaSednice.getDatumSednice().atStartOfDay(defaultZoneId).toInstant()),
                    obrazlozenjeSaSednice.getObrazlozenje()
            );
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Одбацивање иницијативе регистровано у систему");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> ovliceInicijativaIdInicijativeOdbijPut(
            Long idInicijative,
            Obrazlozenje obrazlozenje
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlOdbijInicijativu";
        final String porukaOGresci = "Проблем са завођењем одбијања иницијативе";
        try {
            ovlDajUpisiSesiju();
            dbApi.ovlOdbijInicijativu(
                    jwtHash,
                    idInicijative.intValue(),
                    obrazlozenje.getObrazlozenje()
            );
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Одбијање иницијативе регистровано у систему");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> ovliceInicijativaIdInicijativeOdobriPut(
            Long idInicijative
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlOdobriInicijativu";
        final String porukaOGresci = "Проблем са завођењем одобрења иницијативе";
        try {
            ovlDajUpisiSesiju();
            dbApi.ovlOdobriInicijativu(
                    jwtHash,
                    idInicijative.intValue()
            );
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Одобрење иницијативе регистровано у систему");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<Uspeh> ovliceInicijativaIdInicijativePrihvacenaPut(
            Long idInicijative,
            ObrazlozenjeSaSednice obrazlozenjeSaSednice
    ) throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlRegistrujPrihvacenuInicijativu";
        final String porukaOGresci = "Проблем са завођењем прихватања иницијативе";
        try {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            ovlDajUpisiSesiju();
            dbApi.ovlRegistrujPrihvacenuInicijativu(
                    jwtHash,
                    idInicijative.intValue(),
                    Date.from(obrazlozenjeSaSednice.getDatumSednice().atStartOfDay(defaultZoneId).toInstant()),
                    obrazlozenjeSaSednice.getObrazlozenje())
            ;
            Uspeh odgovor = new Uspeh();
            odgovor.setPoruka("Прихватање иницијативе регистровано у систему");
            ResponseEntity<Uspeh> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<OvliceListaZaIshodOdgovor> ovliceInicijativeZaishodGet() throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlListaZaIshod";
        final String porukaOGresci = "Проблем са припремом листе иницијатива за унос исхода";
        try {
            ovlDajUpisiSesiju();
            String potpisiJSON =
                    dbApi.ovlListaZaIshod(jwtHash);
            OvliceListaZaIshodOdgovor odgovor = new OvliceListaZaIshodOdgovor();
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                List<OsnovnoOInicijativi> inicijativeZaIshod = mapper.readValue(potpisiJSON, new TypeReference<List<OsnovnoOInicijativi>>(){});
                odgovor.setInicijativeZaIshod(inicijativeZaIshod);
            }
            ResponseEntity<OvliceListaZaIshodOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<OvliceListaZaOdobrenjeOdgovor> ovliceInicijativeZaodobrenjeGet() throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlListaZaOdobrenje";
        final String porukaOGresci = "Проблем са припремом листе иницијатива које чекају на одобрење";
        try {
            ovlDajUpisiSesiju();
            String potpisiJSON =
                    dbApi.ovlListaZaOdobrenje(jwtHash);
            OvliceListaZaOdobrenjeOdgovor odgovor = new OvliceListaZaOdobrenjeOdgovor();
            if (potpisiJSON != null) {
                ObjectMapper mapper = JsonMapper.builder()
                        .addModule(new JavaTimeModule())
                        .build();
                List<OsnovnoOInicijativi> inicijativeZaOdobrenje = mapper.readValue(potpisiJSON, new TypeReference<List<OsnovnoOInicijativi>>(){});
                odgovor.setInicijativeZaOdobrenje(inicijativeZaOdobrenje);
            }
            ResponseEntity<OvliceListaZaOdobrenjeOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

    @Override
    public ResponseEntity<OvliceProfilOdgovor> ovliceProfilGet() throws Exception {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ovlProfil";
        final String porukaOGresci = "Читање профила није успело";
        try {
            ovlDajUpisiSesiju();
            NiDatabaseApi.OvlProfil profil =
                    dbApi.ovlDajProfil(jwtHash);
            OvliceProfilOdgovor odgovor = new OvliceProfilOdgovor();
            odgovor.setEmailAdresa(profil.emailAdresa());
            odgovor.setImePrezime(profil.imePrezime());
            odgovor.setNivoUprave(profil.nivoUprave());
            odgovor.setOpisJediniceUprave(profil.opisJediniceUprave());
            ResponseEntity<OvliceProfilOdgovor> response = new ResponseEntity<>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (DataAccessException e) {
            logger.info(imeMetoda,e);
            throw new Exception(porukaOGresci);
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            logger.info(imeMetoda,t);
            throw new Exception(porukaOGresci);
        }
    }

}
