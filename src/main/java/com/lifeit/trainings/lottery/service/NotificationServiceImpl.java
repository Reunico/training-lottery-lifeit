package com.lifeit.trainings.lottery.service;

import com.lifeit.trainings.lottery.config.ApplicationProperties;
import com.lifeit.trainings.lottery.model.Notification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;

    public NotificationServiceImpl(ApplicationProperties applicationProperties, @Qualifier("restTemplate") RestTemplate restTemplate) {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void notify(Long chatId, String text) {
        restTemplate.postForLocation(applicationProperties.getNotifyUrl(), new Notification(chatId, text));
    }
}
