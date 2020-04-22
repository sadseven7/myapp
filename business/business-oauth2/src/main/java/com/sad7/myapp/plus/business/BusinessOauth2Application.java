package com.sad7.myapp.plus.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackageClasses = {BusinessOauth2Application.class}, scanBasePackages = "com.sad7.myapp.plus.cloud.feign")
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(BusinessOauth2Application.class, args);
    }

}
