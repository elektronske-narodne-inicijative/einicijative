package rs.gov.mduls.einicijative.niapi.controllers;

import org.springframework.security.core.Authentication;
import rs.gov.mduls.einicijative.niapi.interfaces.PotpisnikApi;
import rs.gov.mduls.einicijative.niapi.model.PotpisnikProfilOdgovor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.ni.base-path:/niapi}")
public class PotpisnikApiController implements PotpisnikApi {

    private final NativeWebRequest request;

    @Autowired
    public PotpisnikApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<PotpisnikProfilOdgovor> potpisnikProfilGet(
            Authentication auth
    ) {
        // TODO: Hardkodirano za prvi test
        PotpisnikProfilOdgovor odgovor = new PotpisnikProfilOdgovor();
        odgovor.setGodinaRodjenja(1990);
        odgovor.setIdPola(PotpisnikProfilOdgovor.IdPolaEnum.M);
        odgovor.setNazivOpstine("Korisnik='%s' Uloge='%s'".formatted(auth.getName(), auth.getAuthorities()));
        ResponseEntity<PotpisnikProfilOdgovor> response = new ResponseEntity<PotpisnikProfilOdgovor>(odgovor,HttpStatus.OK);
        return response;
    }

}
