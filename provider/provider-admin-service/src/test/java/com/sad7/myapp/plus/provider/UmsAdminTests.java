package com.sad7.myapp.plus.provider;

import com.sad7.myapp.plus.provider.api.UmsAdminService;
import com.sad7.myapp.plus.provider.domain.UmsAdmin;
import com.sad7.myapp.plus.provider.mapper.UmsAdminMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminTests {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private UmsAdminService umsAdminSevice;

    @Test
    public void testSelectAll(){
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        umsAdmins.forEach(umsAdmin -> {
            System.out.println(umsAdmin);
        });
    }

    @Test
    public void testInsert(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("sad7");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        umsAdmin.setIcon("");
        umsAdmin.setEmail("");
        umsAdmin.setNickName("");
        umsAdmin.setNote("");
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        umsAdmin.setStatus(0);

        int result = umsAdminSevice.insert(umsAdmin);
        Assert.assertEquals(result,1);
    }


}
