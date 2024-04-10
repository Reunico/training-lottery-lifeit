package com.lifeit.trainings.lottery.service;

import com.lifeit.trainings.lottery.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryServiceImpl implements LotteryService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public LotteryServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    @Override
    /* Открытие лотереи (начало регистрации */
    public void startRegistration() {
        restTemplate.postForLocation(applicationProperties.getLotteryUrl() + "/start", null);
    }

    /* Окончание регистрации */
    @Override
    public void stopRegistration() {
        restTemplate.postForLocation(applicationProperties.getLotteryUrl() + "/offline", null);
    }

    /* Очистка списка участников */
    @Override
    public void cleanupRegistration() {
        restTemplate.postForLocation(applicationProperties.getLotteryUrl() + "/end", null);
    }
}
