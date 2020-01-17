package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.ApplicationEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApplicationEventRepository extends CrudRepository<ApplicationEvent, Long> {

    @Override
    List<ApplicationEvent> findAll();

    @Override
    long count();

    List<ApplicationEvent> findByDate(LocalDate date);
    List<ApplicationEvent> findByType(ApplicationEvent.EventType type);

    ApplicationEvent save(ApplicationEvent event);
}
