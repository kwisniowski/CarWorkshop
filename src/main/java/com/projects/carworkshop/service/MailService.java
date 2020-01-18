package com.projects.carworkshop.service;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.domain.Mail;
import com.projects.carworkshop.fasade.ApplicationEventFasade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ApplicationEventService applicationEventService;

    public void send(Mail mail) {
        try {
            mailSender.send(createSimpleMailMessage(mail));
            applicationEventService.saveEvent(
                    new ApplicationEvent(
                    ApplicationEvent.EventType.MAIL_SEND,
                    "Mail to:"+mail.getMailTo()+" ,subject: "+mail.getSubject()));
        }
        catch (MailException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    public SimpleMailMessage createSimpleMailMessage(final Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        return message;
    }
}
