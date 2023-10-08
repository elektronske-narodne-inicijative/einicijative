package rs.gov.mduls.einicijative.niapi.api.controllers;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.slf4j.Logger;
import rs.gov.mduls.einicijative.niapi.utils.Consts;
import rs.gov.mduls.einicijative.niapi.utils.NadzorniTrag;
import rs.gov.mduls.einicijative.niapi.utils.Utils;

import java.text.ParseException;

public class ApiControllerBase {

    protected String jwt;
    protected JWT parsedJwt;
    protected String jwtHash;


    protected void pripremiJwt(String authHeader, Logger logger) throws ParseException {

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

    protected void greskaZaNadzorniTrag(String apiMetod, String tipIzuzetka, String porukaGreske) {
        NadzorniTrag.greska(String.format("API-P(%s):%s", apiMetod, tipIzuzetka), porukaGreske);
    }
}
