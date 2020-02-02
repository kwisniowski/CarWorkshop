package com.projects.carworkshop.mfApi;

import com.projects.carworkshop.config.MailConfig;
import com.projects.carworkshop.domain.Mail;
import com.projects.carworkshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    MailConfig mailConfig;

    @Autowired
    MailService mailService;

    @Autowired
    RepairService repairService;
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;
    @Autowired
    SparePartService sparePartService;

    @Value("${info.app.name}")
    private String appFooter;
    @Value("${info.app.version}")
    private String appVersion;

    @Scheduled(cron = "0 0 * * * *")
    public void sendScheduledEmail() {
        mailService.send(new Mail(mailConfig.getAdminMailAddress(), SCHEDULED_MAIL_SUBJECT, "" +
                "Dear Admin,"+
                "\nCurrent database status: "+
                "\nCars in database: "+ repairService.count()+
                "\nCustomers in database: "+customerService.count()+
                "\nRepairs in database: "+repairService.count()+
                "\nSpare Parts in database: "+sparePartService.count()+
                "\n\n Have a nice day, \n"+
                appFooter+", "+appVersion));
    }
}
