package com.lifeit.trainings.lottery.handler;

import com.lifeit.trainings.lottery.constant.ProcessVariableConstant;
import com.lifeit.trainings.lottery.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("notify")
@RequiredArgsConstructor
@Slf4j
public class NotifyHandler implements ExternalTaskHandler {

    private final NotificationService notificationService;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        Long chatId = ((Integer) externalTask.getVariable(ProcessVariableConstant.CHAT_ID)).longValue();
        String text = externalTask.getVariable(ProcessVariableConstant.TEXT);
        notificationService.notify(chatId, text);
        externalTaskService.complete(externalTask);
        log.warn("Уведомления");
    }
}
