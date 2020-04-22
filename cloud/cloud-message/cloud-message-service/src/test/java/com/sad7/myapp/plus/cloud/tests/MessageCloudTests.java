package com.sad7.myapp.plus.cloud.tests;

import com.sad7.myapp.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.sad7.myapp.plus.cloud.message.MessageSource;
import com.sad7.myapp.plus.commons.utils.MapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageCloudTests {


    @Resource
    private MessageSource source;

    @Test
    public boolean testPrintln() throws Exception {
        UmsAdminLoginLogDTO dto = new UmsAdminLoginLogDTO();
        dto.setAdminId(1L);
        dto.setCreateTime(new Date());
        dto.setIp("0.0.0.0");
        dto.setAddress("0.0.0.0");
        dto.setUserAgent("0.0.0.0");
        System.out.println(MapperUtils.obj2json(dto));
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }

}
