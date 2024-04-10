package com.lifeit.trainings.lottery.service;

import com.lifeit.trainings.lottery.model.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getParticipants();
    List<Participant> numerateParticipants(List<Participant> participants);

}
