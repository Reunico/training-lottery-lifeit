package com.lifeit.trainings.lottery.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
@Component
@Setter
@Getter
public class ApplicationProperties {
    private String login;
    private String password;
    private String lotteryUrl;
    private String participantUrl;
    private String numberingUrl;
    private String notifyUrl;
}
