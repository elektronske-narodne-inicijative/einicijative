package rs.gov.mduls.einicijative.niapi.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.gov.mduls.einicijative.niapi.api.interfaces.PotpisnikApi;
import rs.gov.mduls.einicijative.niapi.api.model.PotpisnikProfilOdgovor;
import rs.gov.mduls.einicijative.niapi.db.NiDatabaseApi;
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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.Generated;

import javax.sql.DataSource;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.ni.base-path:/niapi}")
public class PotpisnikApiController implements PotpisnikApi {

    private static final Logger logger = LoggerFactory.getLogger(PotpisnikApiController.class);
    private final NativeWebRequest request;

    @Autowired
    private NiDatabaseApi dbApi;

    private String jwt;
    private String jwtHash;

    @Autowired
    public PotpisnikApiController(NativeWebRequest request)
    {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    private void pripremiJwt() {

        String authHeader = request.getHeader(Consts.HTTP_HEADER_AUTHORIZATION);
        if (authHeader == null) {
            jwt = null;
            jwtHash = null;
            return;
        }
        if (authHeader.startsWith(Consts.HTTP_HEADER_AUTHORIZATION_BEARER)) {
            jwt = authHeader.substring(Consts.HTTP_HEADER_AUTHORIZATION_BEARER.length() + 1);
            jwtHash = Utils.jwtToHash(jwt);
            logger.debug("jwtHash:'{}', jwt: '{}'",jwtHash, jwt);
            return;
        }
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
        NadzorniTrag.prijava("ptp",idKorisnika.toString());
    }

    @Override
    public ResponseEntity<PotpisnikProfilOdgovor> potpisnikProfilGet() {
        long pocetak = System.currentTimeMillis();
        pripremiJwt();
        NiDatabaseApi.Sesija sesija = dbApi.dajSesijuPoHash(jwtHash);
        if (!sesija.prisutna()) {
            // FIXME: izvuci podatke iz jwt, pozovi eUpravu za lične podatke potpisnika - i onda to sve zajedno upiši
            ptpNovaSesija(jwtHash, jwt, new Date(), UUID.randomUUID(), NiDatabaseApi.IdPolaEnum.M, 1990, "70114");
        }
        NiDatabaseApi.PtpProfil profil = dbApi.ptpDajProfil(jwtHash);
        // Upiši rezultat u DTO i vrati ga klijentu
        PotpisnikProfilOdgovor odgovor = new PotpisnikProfilOdgovor();
        odgovor.setGodinaRodjenja(profil.godinaRodjenja());
        odgovor.setNazivOpstine(profil.nazivOpstine());
        if (profil.idPola() == NiDatabaseApi.IdPolaEnum.M) {
            odgovor.setIdPola(PotpisnikProfilOdgovor.IdPolaEnum.M);
        } else {
            odgovor.setIdPola(PotpisnikProfilOdgovor.IdPolaEnum.Z);
        }
        ResponseEntity<PotpisnikProfilOdgovor> response = new ResponseEntity<PotpisnikProfilOdgovor>(odgovor,HttpStatus.OK);
        Nadgledanje.apiPoziv("potpisnikProfilGet",System.currentTimeMillis() - pocetak);
        return response;
    }

}
