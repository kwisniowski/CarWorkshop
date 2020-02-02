package com.projects.carworkshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "EVENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEvent {
    public enum EventType {MAIL_SEND,CREATED,DELETED,UPDATED}
    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID")
    private long id;

    @Column(name="EVENT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private EventType type;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TIME")
    private LocalTime time;

    @Column(name = "info")
    private String info;

    public ApplicationEvent(@NotNull EventType type, @NotNull LocalDate date, @NotNull LocalTime time) {
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public ApplicationEvent(@NotNull EventType type, @NotNull String info) {
        this.type = type;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.info = info;
    }
}
