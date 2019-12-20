package com.aho.gestionabscence.batchBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@ToString
public class SmsRequest {

    @NotBlank
    private final String phoneNumber;
    @NotBlank
    private final String message;
}
