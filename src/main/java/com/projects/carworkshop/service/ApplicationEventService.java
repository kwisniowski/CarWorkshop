package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.repository.ApplicationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationEventService {

    @Autowired
    ApplicationEventRepository repository;

    public List<ApplicationEvent> getAllEvents() {
        return repository.findAll();
    }

    public long countEvents() {
        return repository.count();
    }

    public List<ApplicationEvent> findEventByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<ApplicationEvent> findEventByType(ApplicationEvent.EventType type) {
        return repository.findByType(type);
    }

    public ApplicationEvent saveEvent(ApplicationEvent applicationEvent) {
        return repository.save(applicationEvent);
    }

    public void deleteAll () {
        repository.deleteAll();
    }
}
