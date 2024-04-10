package com.lifeit.trainings.lottery.handler;

import com.lifeit.trainings.lottery.constant.ErrorConstant;
import com.lifeit.trainings.lottery.service.LotteryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ExternalTaskSubscription("start-registration")
@RequiredArgsConstructor
public class StartRegistrationHandler implements ExternalTaskHandler {

    private final LotteryService lotteryService;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
            lotteryService.startRegistration();
            externalTaskService.complete(externalTask);
            log.warn("Регистрация открыта");
        } catch (Exception e) {
            log.error("Ошибка соединения");
            externalTaskService.handleBpmnError(externalTask, ErrorConstant.CONNECT_ERROR);
        }

    }
}
