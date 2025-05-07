package steps;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class RequisicaoBuilder {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers;

    public RequisicaoBuilder(String usuario, String senha) {
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String credenciais = usuario + ":" + senha;
        String encoded = Base64.getEncoder().encodeToString(credenciais.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encoded);
    }

    public ResponseEntity<String> post(String endpoint, String jsonBody) {
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        return restTemplate.postForEntity(endpoint, entity, String.class);
    }

    public ResponseEntity<String> get(String endpoint) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> put(String endpoint, String jsonBody) {
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        return restTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);
    }


    public HttpHeaders getHeaders() {
        return headers;
    }
}
