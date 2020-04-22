package com.sad7.myapp.plus.cloud.feign;

import com.sad7.myapp.plus.cloud.feign.fallback.MessageFeignFallback;
import com.sad7.myapp.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cloud-message", path = "message", configuration = FeignRequestConfiguration.class, fallback = MessageFeignFallback.class)
public interface MessageFeign {
}
