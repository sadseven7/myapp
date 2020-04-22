package com.sad7.myapp.plus.provider.service;

import com.sad7.myapp.plus.provider.domain.UmsAdminLoginLog;
import javax.annotation.Resource;
import com.sad7.myapp.plus.provider.mapper.UmsAdminLoginLogMapper;
import com.sad7.myapp.plus.provider.api.UmsAdminLoginLogService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}
