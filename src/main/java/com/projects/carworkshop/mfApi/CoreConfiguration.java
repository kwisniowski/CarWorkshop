package com.projects.carworkshop.mfApi;

import com.projects.carworkshop.config.MailConfig;
import com.projects.carworkshop.domain.Mail;
import com.projects.carworkshop.service.MailService;
import com.projects.carworkshop.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class CoreConfiguration {

    public final String SCHEDULED_MAIL_SUBJECT = "CarWorkshop App: Scheduled Email";
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RentRequestService rentRequestService;

    @Autowired
    MailConfig mailConfig;

    @Autowired
    MailService mailService;

    @Scheduled(cron = "*/59 * * * * *")
    public void sendScheduledEmail() {
        mailService.send(new Mail(mailConfig.getAdminMailAddress(), SCHEDULED_MAIL_SUBJECT, "" +
                "Current app status: /n" +
                "RentsRequests in database: "+rentRequestService.countRequests()));
    }
}
