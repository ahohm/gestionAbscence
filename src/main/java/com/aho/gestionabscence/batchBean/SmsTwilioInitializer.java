package com.aho.gestionabscence.batchBean;

import com.twilio.Twilio;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SmsTwilioInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsTwilioInitializer.class);

    private final SmsTwilioConfig smsTwilioConfig;

    @Autowired
    public SmsTwilioInitializer(SmsTwilioConfig smsTwilioConfig) {
        this.smsTwilioConfig = smsTwilioConfig;
        Twilio.init(
                smsTwilioConfig.getAccountSid(),
                smsTwilioConfig.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account_sid {}", smsTwilioConfig.getAccountSid());
    }
}
