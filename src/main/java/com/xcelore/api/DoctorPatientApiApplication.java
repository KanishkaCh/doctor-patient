package com.xcelore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoctorPatientApiApplication {
    private static String[] args;

    public static void main(String[] args) {
        DoctorPatientApiApplication.args = args;
        SpringApplication.run(DoctorPatientApiApplication.class, args);
    }
}
