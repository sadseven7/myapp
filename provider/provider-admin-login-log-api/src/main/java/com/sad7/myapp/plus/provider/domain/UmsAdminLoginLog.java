package com.sad7.myapp.plus.provider.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 系统登录日志
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @date 2019-10-19 03:49:10
 * @see com.sad7.myapp.plus.provider.domain
 */
@Data
@Table(name = "ums_admin_login_log")
public class UmsAdminLoginLog implements Serializable {


    private static final long serialVersionUID = -6718581089122435488L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "ip")
    private String ip;

    @Column(name = "address")
    private String address;

    /**
     * 浏览器登录类型
     */
    @Column(name = "user_agent")
    private String userAgent;
}