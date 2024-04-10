package com.lifeit.trainings.lottery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    private String name;
    private Instant createdDate;
    private Long chatId;
    private Long number;
}
