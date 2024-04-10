package com.lifeit.trainings.lottery.client;

import com.lifeit.trainings.lottery.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class RestTemplateAuthInterceptor implements ClientHttpRequestInterceptor {

    private final ApplicationProperties applicationProperties;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        String auth = applicationProperties.getLogin() + ":" + applicationProperties.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.UTF_8) );
        String authHeader = "Basic " + new String( encodedAuth );
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, authHeader);
        return execution.execute(request, body);
    }
}
