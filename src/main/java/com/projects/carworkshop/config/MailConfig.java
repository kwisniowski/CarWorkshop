package com.projects.carworkshop.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MailConfig {

    @Value("wisniowski.kacper@gmail.com")
    private String adminMailAddress;

    @Value("${spring.mail.password}")
    private String adminMailPassword;
}
