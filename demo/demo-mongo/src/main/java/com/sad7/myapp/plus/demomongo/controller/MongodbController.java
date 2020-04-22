package com.sad7.myapp.plus.demomongo.controller;

import com.google.common.collect.ImmutableMap;
import com.sad7.myapp.plus.demomongo.dto.LoginLog;
import com.sad7.myapp.plus.tool.mongodb.service.MongoDbOperatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/loginLog")
@Slf4j
public class MongodbController {
    @Autowired
    private MongoTemplate template;

    @Reference(version = "${services.versions.search.v1}")
    private MongoDbOperatorServiceImpl<LoginLog> mongoDbOperator;

    @PostConstruct
    void init() {
        mongoDbOperator = new MongoDbOperatorServiceImpl<>(template, LoginLog.class);
    }

    /**
     * 使用MongoTemplate查询数据
     */
//    @ApiOperation(value = "获取用户的登陆日志", notes = "获取用户的登陆日志")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(String key) {
        Query query = new Query();
        query.addCriteria(Criteria.where("customerId").is(Integer.parseInt(key)));
        List<LoginLog> clientDevices = template.find(query, LoginLog.class);

        return clientDevices.toString();
    }

//    @ApiOperation(value = "获取用户的登陆日志2", notes = "获取用户的登陆日志2")
    @RequestMapping(value = "/list2",method = RequestMethod.GET)
    public String list2(String key) {
        LoginLog model = mongoDbOperator.getOne(ImmutableMap.of("customerId", Integer.parseInt(key)));

        return model.toString();
    }

//    @ApiOperation(value = "新增用户登录日志", notes = "新增用户登录日志")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(String customerId) {
        LoginLog model = new LoginLog();
        model.setId(String.valueOf(mongoDbOperator.getNextSequence()));
        model.setCustomerId(customerId);
        model.setLastLoginTime(System.currentTimeMillis());
        mongoDbOperator.insert(model);

        return model.toString();
    }
}