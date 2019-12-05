package com.aho.gestionabscence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionAbscenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionAbscenceApplication.class, args);
    }

}