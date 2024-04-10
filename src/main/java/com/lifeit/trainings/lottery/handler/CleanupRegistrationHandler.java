package com.lifeit.trainings.lottery.handler;

import com.lifeit.trainings.lottery.service.LotteryService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExternalTaskSubscription("cleanup-registration")
@RequiredArgsConstructor
public class CleanupRegistrationHandler implements ExternalTaskHandler {

    private final LotteryService lotteryService;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        lotteryService.cleanupRegistration();
        externalTaskService.complete(externalTask);
    }
}
