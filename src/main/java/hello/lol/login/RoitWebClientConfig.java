package hello.lol.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Component
@Slf4j
public class RoitWebClientConfig {
    @Value("${external.riot.api-key}")
    private String apiKey;

    @Bean
    public RestTemplate riotKrRestTemplate() {
        return createRiotRestTemplate("kr");
    }

    @Bean
    public RestTemplate riotAsiaRestTemplate() {
        return createRiotRestTemplate("asia");
    }

    private RestTemplate createRiotRestTemplate(String region) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

        // API 요청에 필요한 헤더를 인터셉터로 추가
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            request.getHeaders().set("X-Riot-Token", apiKey);
            request.getHeaders().set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
            request.getHeaders().set(HttpHeaders.ACCEPT_LANGUAGE, "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
            request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            request.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            return execution.execute(request, body);
        };

        restTemplate.setInterceptors(Collections.singletonList(interceptor));

        // 기본 URL 설정은 RestTemplate에서 직접 지원하지 않으므로 사용 시 URL을 완성해야 함
        // baseUrl은 서비스 클래스에서 String.format("https://%s.api.riotgames.com", region) + "/path" 형태로 사용

        return restTemplate;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return factory;
    }
}
