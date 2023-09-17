package rs.gov.mduls.einicijative.niapi.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import rs.gov.mduls.einicijative.niapi.utils.Consts;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class WebSecurityConfig {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    SecurityFilterChain resourceServerSecurityFilterChain(
            HttpSecurity http,
            @Value("${resource-server.cors.allowed-origins:}#{T(java.util.Collections).emptyList()}") List<String> allowedOrigins
    ) throws Exception
    {
        http.oauth2ResourceServer(
                authorize -> authorize.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())
                )
        );

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(csrf -> csrf.disable());
        http.exceptionHandling(handeling -> handeling.authenticationEntryPoint((request, response, authException) -> {
            response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Restricted Content\"");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }));

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/niapi/status").permitAll()
                .requestMatchers("/salteri/**").permitAll() // "ručna" validacija hedera
                .requestMatchers("/niapi/potpisnik/**").authenticated()
                .requestMatchers("/niapi/inicijator/**").authenticated()
                .requestMatchers("/niapi/ovlice/**").authenticated()
        );

        http.cors(cors -> {
            if (allowedOrigins.isEmpty()) {
                cors.disable();
            } else {
                cors.configurationSource(corsConfig(allowedOrigins));
            }
        });
        return http.build();
    }

    CorsConfigurationSource corsConfig(List<String> allowedOrigins) {
        final var source = new UrlBasedCorsConfigurationSource();

        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}