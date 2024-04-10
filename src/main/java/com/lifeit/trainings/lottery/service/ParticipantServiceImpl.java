package com.lifeit.trainings.lottery.service;

import com.lifeit.trainings.lottery.config.ApplicationProperties;
import com.lifeit.trainings.lottery.model.Participant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public ParticipantServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public List<Participant> getParticipants() {
        return (List<Participant>) restTemplate.getForObject(applicationProperties.getParticipantUrl(), List.class);
    }

    @Override
    public List<Participant> numerateParticipants(List<Participant> participants) {
        return (List<Participant>) restTemplate.postForObject(applicationProperties.getParticipantUrl(), participants, List.class);
    }
}
