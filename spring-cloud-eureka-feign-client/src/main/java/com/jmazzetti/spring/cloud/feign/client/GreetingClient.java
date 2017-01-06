package com.jmazzetti.spring.cloud.feign.client;

import com.jmazzetti.spring.cloud.eureka.client.EurekaClientRemoteController;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("spring-cloud-eureka-client")
public interface GreetingClient extends EurekaClientRemoteController {
}
