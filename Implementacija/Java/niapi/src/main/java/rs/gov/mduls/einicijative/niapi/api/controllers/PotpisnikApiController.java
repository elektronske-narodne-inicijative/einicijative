package rs.gov.mduls.einicijative.niapi.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.gov.mduls.einicijative.niapi.api.interfaces.PotpisnikApi;
import rs.gov.mduls.einicijative.niapi.api.model.InicijativaZaPotpis;
import rs.gov.mduls.einicijative.niapi.api.model.PotpisnikPotpisOdgovor;
import rs.gov.mduls.einicijative.niapi.api.model.PotpisnikProfilOdgovor;
import rs.gov.mduls.einicijative.niapi.clients.euprava.EupravaRESTClient;
import rs.gov.mduls.einicijative.niapi.clients.euprava.dto.DetaljiOGradjaninu;
import rs.gov.mduls.einicijative.niapi.db.NiDatabaseApi;
import rs.gov.mduls.einicijative.niapi.pdf.PdfUsluge;
import rs.gov.mduls.einicijative.niapi.utils.Consts;
import rs.gov.mduls.einicijative.niapi.utils.Nadgledanje;
import rs.gov.mduls.einicijative.niapi.utils.NadzorniTrag;
import rs.gov.mduls.einicijative.niapi.utils.Utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.ZoneOffset;
import java.util.*;

import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.ni.base-path:/niapi}")
public class PotpisnikApiController implements PotpisnikApi {

    private static final Logger logger = LoggerFactory.getLogger(PotpisnikApiController.class);
    private final NativeWebRequest request;

    @Autowired
    private NiDatabaseApi dbApi;


    @Autowired
    private PdfUsluge pdfUsluge;

    @Autowired
    private EupravaRESTClient eupravaServis;

    private String jwt;
    private JWT parsedJwt;
    private String jwtHash;

    @Autowired
    public PotpisnikApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    private void pripremiJwt() throws ParseException {

        String authHeader = request.getHeader(Consts.HTTP_HEADER_AUTHORIZATION);
        if (authHeader == null) {
            jwt = null;
            jwtHash = null;
            return;
        }
        if (authHeader.startsWith(Consts.HTTP_HEADER_AUTHORIZATION_BEARER)) {
            jwt = authHeader.substring(Consts.HTTP_HEADER_AUTHORIZATION_BEARER.length() + 1);
            jwtHash = Utils.jwtToHash(jwt);
            parsedJwt = JWTParser.parse(jwt);
            logger.debug("jwtHash:'{}', parsedJwt.expiry:'{}' jwt:'{}'", jwtHash, parsedJwt.getJWTClaimsSet().getExpirationTime().toString(), jwt);
            return;
        }
    }

    private void greskaZaNadzorniTrag(String apiMetod, String tipIzuzetka, String porukaGreske) {
        NadzorniTrag.greska(String.format("API-P(%s):", apiMetod, tipIzuzetka), porukaGreske);
    }

    private void ptpNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            NiDatabaseApi.IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine
    ) {
        // prvi metod sa novom sesijom šalje događaj prijave u nadzorni dnevnik
        dbApi.ptpNovaSesija(jwtHash, jwtVrednost, trnIstekaJWT, idKorisnika, idPola, godinaRodjenja, idOpstine);
        NadzorniTrag.prijava("ptp", idKorisnika.toString());
    }

    private void ptpDajUpisiSesiju() throws ParseException, URISyntaxException, IOException, InterruptedException {
        pripremiJwt();
        NiDatabaseApi.Sesija sesija = dbApi.dajSesijuPoHash(jwtHash);
        if (!sesija.prisutna()) {
            DetaljiOGradjaninu detalji = eupravaServis.dajPotpisnika(jwt);
            ptpNovaSesija(
                    jwtHash,
                    jwt,
                    parsedJwt.getJWTClaimsSet().getExpirationTime(),
                    detalji.getIdKorisnika(),
                    NiDatabaseApi.IdPolaEnum.fromValue(detalji.getIdPola().getValue()),
                    detalji.getGodinaRodjenja(),
                    detalji.getIdOpstine()
            );
        }
    }

    @Override
    public ResponseEntity<PotpisnikProfilOdgovor> potpisnikProfilGet() {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ptpProfil";
        try {
            ptpDajUpisiSesiju();
            NiDatabaseApi.PtpProfil profil =
                    dbApi.ptpDajProfil(jwtHash);
            PotpisnikProfilOdgovor odgovor = new PotpisnikProfilOdgovor();
            odgovor.setGodinaRodjenja(profil.godinaRodjenja());
            odgovor.setNazivOpstine(profil.nazivOpstine());
            if (profil.idPola() == NiDatabaseApi.IdPolaEnum.M) {
                odgovor.setIdPola(PotpisnikProfilOdgovor.IdPolaEnum.M);
            } else {
                odgovor.setIdPola(PotpisnikProfilOdgovor.IdPolaEnum.Z);
            }
            ResponseEntity<PotpisnikProfilOdgovor> response = new ResponseEntity<PotpisnikProfilOdgovor>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            return null;
        }
    }

    @Override
    public ResponseEntity<PotpisnikPotpisOdgovor> potpisnikPotpisPost(
            InicijativaZaPotpis inicijativaZaPotpis
    ) {
        long pocetak = System.currentTimeMillis();
        final String imeMetoda = "ptpPotpis";
        try {
            ptpDajUpisiSesiju();
            NiDatabaseApi.PtpPotpis potpis =
                    dbApi.ptpPotpisiInicijativu(jwtHash, inicijativaZaPotpis.getIdInicijative());
            if(potpis.idPotpisa() == null) {
                ResponseEntity<String> response = new ResponseEntity<String>("Потписивање није успело", HttpStatus.BAD_REQUEST);
            }
            PotpisnikPotpisOdgovor odgovor = new PotpisnikPotpisOdgovor();
            odgovor.setIdInicijative(inicijativaZaPotpis.getIdInicijative());
            odgovor.setIdPotpisa(potpis.idPotpisa());
            odgovor.setTrnZavodjenjaPotpisa(potpis.trnZavodjenjaPotpisa().toInstant().atOffset(ZoneOffset.UTC)); //FIXME: CET/CEST
            odgovor.setOveraAplikacije(pdfUsluge.pripremiPotvrduOPotpisu(inicijativaZaPotpis.getIdInicijative(),potpis));
            ResponseEntity<PotpisnikPotpisOdgovor> response = new ResponseEntity<PotpisnikPotpisOdgovor>(odgovor, HttpStatus.OK);
            Nadgledanje.apiPoziv(imeMetoda, System.currentTimeMillis() - pocetak);
            return response;
        } catch (Throwable t) {
            greskaZaNadzorniTrag(imeMetoda, t.getClass().getName(), t.getMessage());
            return null;
        }
    }
}