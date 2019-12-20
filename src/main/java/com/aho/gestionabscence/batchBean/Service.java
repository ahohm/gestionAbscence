package com.aho.gestionabscence.batchBean;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {

    private final  SmsSender smsSender;

    public void sendSms(@Qualifier("twilio") SmsRequest smsRequest){
            smsSender.sendSms(smsRequest);
    }

}
