package rs.gov.mduls.einicijative.niapi.api.controllers;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import rs.gov.mduls.einicijative.niapi.api.interfaces.StatusApi;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.ni.base-path:/niapi}")
public class StatusApiController implements StatusApi {

    private final NativeWebRequest request;

    @Autowired
    public StatusApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<String> statusGet() {
        String status = "OK";
        ResponseEntity response = new ResponseEntity<String>(status, HttpStatus.OK);
        return response;
    }

}
