package com.sad7.myapp.plus.business.controller;


import com.sad7.myapp.plus.commons.ResponseResult;
import com.sad7.myapp.plus.provider.api.UmsAdminService;
import com.sad7.myapp.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户注册.
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @date 2019-07-26 11:24:44
 * @see com.sad7.myapp.plus.business.controller
 */
@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminSevice;

    /**
     * 注册
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "")
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin){

        //验证用户是否已存在
        String message = validateReg(umsAdmin);

        //通过验证
        if (message == null){
            int result = umsAdminSevice.insert(umsAdmin);

            //注册成功
            if(result > 0){
                UmsAdmin admin = umsAdminSevice.get(umsAdmin.getUsername());
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(),"用户注册成功",admin);
            }
        }

        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(),message != null ? message : "用户注册失败");
    }

    /**
     * 注册信息验证
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return 验证结果
     */
    private String validateReg(UmsAdmin umsAdmin) {
        UmsAdmin admin = umsAdminSevice.get(umsAdmin.getUsername());

        if(admin != null){
            return "用户已存在，请重新输入";
        }
        return null;
    }
}
