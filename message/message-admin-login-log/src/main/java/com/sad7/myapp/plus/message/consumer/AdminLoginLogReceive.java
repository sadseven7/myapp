package com.sad7.myapp.plus.message.consumer;

import com.sad7.myapp.plus.commons.utils.MapperUtils;
import com.sad7.myapp.plus.provider.api.UmsAdminLoginLogService;
import com.sad7.myapp.plus.provider.domain.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginLogReceive {

    @Reference(version = "1.0.0")
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @StreamListener("admin-login-log-topic")
    public void receiveAdminLoginLog(String umsAdminLoginLogJson) throws Exception {
        UmsAdminLoginLog umsAdminLoginLog = MapperUtils.json2pojo(umsAdminLoginLogJson, UmsAdminLoginLog.class);
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }

}