package sphe.dev.restdemo.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sphe.dev.restdemo.service.InseeAuthApiResponse;

import java.util.Objects;

@Configuration
public class InseeSirenApiClientConfig {

    @Value("${insee.api.consumer-key}")
    private String customerKey;

    @Value("${insee.api.consumer-secret}")
    private String customerSecret;

    @Value("${insee.api.token-url}")
    private String tokenURL;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            String accessToken = generateAccessToken();
            template.header("Authorization", "Bearer " + accessToken);
        };
    }

    private String generateAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(customerKey, customerSecret);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<InseeAuthApiResponse> response = restTemplate.postForEntity(tokenURL, request, InseeAuthApiResponse.class);
        return Objects.requireNonNull(response.getBody()).getAccessToken();
    }

}
