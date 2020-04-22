package com.sad7.myapp.plus.cloud.feign.fallback;

import com.sad7.myapp.plus.cloud.feign.MessageFeign;
import org.springframework.stereotype.Component;

@Component
public class MessageFeignFallback implements MessageFeign {

    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

}