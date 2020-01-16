package com.projects.carworkshop.dto;

import com.projects.carworkshop.domain.ApplicationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEventDto {
    private enum EventType {SEND,CREATED,DELETED,UPDATED}

    private long id;
    private ApplicationEvent.EventType type;
    private LocalDate date;
    private LocalTime time;
    private String info;
}
