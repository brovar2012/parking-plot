package com.andersenlab.parking_system_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParkingSystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingSystemServiceApplication.class, args);
    }

}
