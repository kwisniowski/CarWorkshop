package com.projects.carworkshop.controller;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.domain.Mail;
import com.projects.carworkshop.dto.ApplicationEventDto;
import com.projects.carworkshop.fasade.ApplicationEventFasade;
import com.projects.carworkshop.service.MailService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/carworkshop/api")
public class ApplicationEventController {

    @Autowired
    ApplicationEventFasade fasade;
    @Autowired
    MailService mailService;

    @GetMapping(value = "/events")
    public List<ApplicationEventDto> getAllEvents() {
        return fasade.getAllEvents();
    }

    @GetMapping(value = "/events/byType/{type}")
    public List<ApplicationEventDto> getEventsByType(@PathVariable String type) {
        if (validateTypeString(type)) {
            return fasade.findEventByType(ApplicationEvent.EventType.valueOf(type.toUpperCase()));
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/events/byDate/{date}")
    public List<ApplicationEventDto> getEventsByDate(@PathVariable String date) {
        return fasade.findEventByDate(LocalDate.parse(date));
    }

    @PostMapping(value = "/events")
    public ApplicationEventDto createApplicationEvent (@RequestBody ApplicationEventDto applicationEventDto) {
        ApplicationEventDto returnedDto = fasade.createApplicationEvent(applicationEventDto);
        return returnedDto;
    }

    private boolean validateTypeString (String type) {
        boolean isCorrectString = false;
        for (ApplicationEvent.EventType enumType:ApplicationEvent.EventType.values()) {
            if (type.equalsIgnoreCase(enumType.toString())) {
                isCorrectString = true;
            }
        }
        return isCorrectString;
    }
}
