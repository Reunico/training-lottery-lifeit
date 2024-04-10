package com.lifeit.trainings.lottery.config;

import com.lifeit.trainings.lottery.client.RestTemplateAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticatedRestTemplateConfig {

    private final ApplicationProperties applicationProperties;

    @Bean("restTemplate")
    public RestTemplate authenticatedRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateAuthInterceptor(applicationProperties));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
