package com.projects.carworkshop.repository;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.fasade.ApplicationEventFasade;
import com.projects.carworkshop.service.ApplicationEventService;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationEventTestSuite {

    @Autowired
    ApplicationEventFasade fasade;
    @Autowired
    ApplicationEventService service;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8084");
        webServer.start();
    }

    @Test
    public void shouldSaveApplicationEvent() {

        //Given
        ApplicationEvent testEvent = new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                LocalDate.now(), LocalTime.now());
        service.saveEvent(testEvent);

        //When
        long expectedId = testEvent.getId();

        //Then
        Assert.assertNotNull(testEvent.getId());
    }

    @Test
    public void shouldFetchEventsList() {

        //Given
        ApplicationEvent testEvent1 = new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                LocalDate.now().plus(1, ChronoUnit.DAYS), LocalTime.now());
        ApplicationEvent testEvent2 = new ApplicationEvent(ApplicationEvent.EventType.CREATED,
                LocalDate.now(), LocalTime.now());
        List<ApplicationEvent> eventsList = new ArrayList<>();
        eventsList.add(testEvent2);

        //When
        service.saveEvent(testEvent1);
        service.saveEvent(testEvent2);
        eventsList = service.getAllEvents();

        //Then
        Assert.assertEquals(2,eventsList.size());
    }
}
