package com.projects.carworkshop.fasade;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.dto.ApplicationEventDto;
import com.projects.carworkshop.mapper.ApplicationEventMapper;
import com.projects.carworkshop.service.ApplicationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApplicationEventFasade {

    @Autowired
    ApplicationEventMapper mapper;

    @Autowired
    ApplicationEventService service;

    public List<ApplicationEventDto> getAllEvents() {
        return mapper.mapToApplicationEventDtoList(service.getAllEvents());
    }

    public long countEvents() {
        return service.countEvents();
    }

    public List<ApplicationEventDto> findEventByDate(LocalDate date) {
        return mapper.mapToApplicationEventDtoList(service.findEventByDate(date));
    }

    public List<ApplicationEventDto> findEventByType(ApplicationEvent.EventType type) {
        return mapper.mapToApplicationEventDtoList(service.findEventByType(type));
    }

    public ApplicationEventDto createApplicationEvent (ApplicationEventDto applicationEventDto) {
        return mapper.mapToApplicationEventDto(service.saveEvent(mapper.mapToApplicationEvent(applicationEventDto)));
    }

    public void clearEventsList() {
        service.deleteAll();
    }
}
