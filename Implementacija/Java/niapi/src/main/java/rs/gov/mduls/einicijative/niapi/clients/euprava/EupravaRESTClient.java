package rs.gov.mduls.einicijative.niapi.clients.euprava;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.gov.mduls.einicijative.niapi.clients.euprava.dto.DetaljiOGradjaninu;
import rs.gov.mduls.einicijative.niapi.clients.euprava.dto.DetaljiOOvlascenomLicu;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component("eUpravaRESTClient")
public class EupravaRESTClient {

    private static final Logger logger = LoggerFactory.getLogger(EupravaRESTClient.class);

    @Value("${euprava.url}")
    String eUpravaURL;

    @Value("${euprava.timeout}")
    Long eUpravaTimeoutSeconds;

    @Value("${euprava.salter-username}")
    String eUpravaSalterUsername;

    @Value("${euprava.salter-password}")
    String eUpravaSalterPassword;

    public String dajHttpGetOdgovorSaJWTAutentikacijom(String urlMetode, String jwt) throws URISyntaxException, IOException, InterruptedException {

        logger.debug("URL='{}', timeout={}, jwt='{}'",urlMetode,eUpravaTimeoutSeconds,jwt);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlMetode))
                .timeout(Duration.of(eUpravaTimeoutSeconds, SECONDS))
                .header("Authorization", String.format("Bearer %s",jwt))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug("Response='{}'",response.body());
        return response.body();
    }

    public String dajHttpGetOdgovorSaBaznomAutentikacijom(String urlMetode) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlMetode))
                .timeout(Duration.of(eUpravaTimeoutSeconds, SECONDS))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .authenticator(
                        new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(
                                        eUpravaSalterUsername,
                                        eUpravaSalterPassword.toCharArray()
                                );
                            }
                        }
                ).build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public DetaljiOGradjaninu dajPotpisnika(String jwt) throws URISyntaxException, IOException, InterruptedException {

        String urlMetode = String.format("%s%s",this.eUpravaURL,"/potpisnik");
        String odgovor = dajHttpGetOdgovorSaJWTAutentikacijom(urlMetode, jwt);
        ObjectMapper mapper = new ObjectMapper();
        DetaljiOGradjaninu detaljiOGradjaninu = mapper.readValue(odgovor,DetaljiOGradjaninu.class);
        return detaljiOGradjaninu;
    }

    public DetaljiOGradjaninu dajInicijatora(String jwt) throws URISyntaxException, IOException, InterruptedException {

        String urlMetode = String.format("%s%s",this.eUpravaURL,"/inicijator");
        String odgovor = dajHttpGetOdgovorSaJWTAutentikacijom(urlMetode, jwt);
        ObjectMapper mapper = new ObjectMapper();
        DetaljiOGradjaninu detaljiOGradjaninu = mapper.readValue(odgovor,DetaljiOGradjaninu.class);
        return detaljiOGradjaninu;
    }

    public DetaljiOOvlascenomLicu dajOvlascenoLice(String jwt) throws URISyntaxException, IOException, InterruptedException {

        String urlMetode = String.format("%s%s",this.eUpravaURL,"/ovlice");
        String odgovor = dajHttpGetOdgovorSaJWTAutentikacijom(urlMetode, jwt);
        ObjectMapper mapper = new ObjectMapper();
        DetaljiOOvlascenomLicu detaljiOOvlascenomLicu = mapper.readValue(odgovor,DetaljiOOvlascenomLicu.class);
        return detaljiOOvlascenomLicu;
    }

    public DetaljiOGradjaninu dajPotpisnikaZaSalter(String jmbg) throws URISyntaxException, IOException, InterruptedException {

        String urlMetode = String.format("%s%s%s%s",this.eUpravaURL,"/potpisnik/",jmbg,"/salter");
        String odgovor = dajHttpGetOdgovorSaBaznomAutentikacijom(urlMetode);
        ObjectMapper mapper = new ObjectMapper();
        DetaljiOGradjaninu detaljiOGradjaninu = mapper.readValue(odgovor,DetaljiOGradjaninu.class);
        return detaljiOGradjaninu;
    }
}
