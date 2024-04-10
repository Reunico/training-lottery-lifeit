package com.lifeit.trainings.lottery.handler;

import com.lifeit.trainings.lottery.constant.ProcessVariableConstant;
import com.lifeit.trainings.lottery.model.Participant;
import com.lifeit.trainings.lottery.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@ExternalTaskSubscription("numerate-participants")
@RequiredArgsConstructor
public class NumerateParticipantsHandler implements ExternalTaskHandler {

    private final ParticipantService participantService;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        List<Participant> participants = externalTask.getVariable(ProcessVariableConstant.PARTICIPANTS);
        participants = participantService.numerateParticipants(participants);
        externalTaskService.complete(externalTask, Map.of(ProcessVariableConstant.PARTICIPANTS, participants));
        log.warn("Присвоены номера участникам");
    }
}
