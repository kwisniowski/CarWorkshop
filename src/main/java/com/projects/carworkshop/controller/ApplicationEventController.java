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

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public List<ApplicationEventDto> getAllEvents() {
        return fasade.getAllEvents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events/byType/{type}")
    public List<ApplicationEventDto> getEventsByType(@PathVariable String type) {
        if (validateTypeString(type)) {
            return fasade.findEventByType(ApplicationEvent.EventType.valueOf(type.toUpperCase()));
        }
        else return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events/byDate/{date}")
    public List<ApplicationEventDto> getEventsByDate(@PathVariable String date) {
        return fasade.findEventByDate(LocalDate.parse(date));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/events")
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
