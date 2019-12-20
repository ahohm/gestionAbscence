package com.aho.gestionabscence.batchBean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties("twilio")
public class SmsTwilioConfig {

    private String  accountSid;
    private String  authToken;
    private String  trialNumber;
}
