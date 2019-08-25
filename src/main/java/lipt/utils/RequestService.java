package lipt.utils;

import lipt.model.Coordinate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class RequestService {

    @Value("${service.url}")
    private String serviceUrl;

    @Value("${service.token}")
    private String token;

    @Value("${service.secret}")
    private String xSecret;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.create(serviceUrl);
    }

    public Coordinate makeRequest(String placeName) {
        String[] arr = new String[]{placeName};

        Mono<Coordinate[]> coordinates = webClient.post()
                .uri(serviceUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(arr), String[].class)
                .header(HttpHeaders.AUTHORIZATION, token)
                .header("X-Secret", xSecret)
                .retrieve()
                .bodyToMono(Coordinate[].class)
                .log();

        Coordinate[] res = coordinates.block();

        return  (res == null || res.length == 0) ? new Coordinate() : res[0];
    }

}
