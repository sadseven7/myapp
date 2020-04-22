package com.sad7.myapp.plus.cloud.api;

import com.sad7.myapp.plus.cloud.dto.UmsAdminLoginLogDTO;

public interface MessageService {
    boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO);
}
