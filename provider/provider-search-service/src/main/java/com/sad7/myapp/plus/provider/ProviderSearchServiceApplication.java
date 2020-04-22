package com.sad7.myapp.plus.provider;

import com.sad7.myapp.plus.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackageClasses = {ProviderSearchServiceApplication.class, DubboSentinelConfiguration.class})
@MapperScan(basePackages = "com.sad7.myapp.plus.provider.mapper")
public class ProviderSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderSearchServiceApplication.class, args);
    }

}
