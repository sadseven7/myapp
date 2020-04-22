package com.sad7.myapp.plus.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SearchConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchConsumerApplication.class, args);
    }

}
