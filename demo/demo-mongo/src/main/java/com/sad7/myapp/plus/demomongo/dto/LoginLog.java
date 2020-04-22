package com.sad7.myapp.plus.demomongo.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "login_log")
@Data
public class LoginLog {
    @Id
    private String id;
    private String customerId;
    private long lastLoginTime;
}
