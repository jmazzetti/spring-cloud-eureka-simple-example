package com.jmazzetti.spring.cloud.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface EurekaClientRemoteController {
    @RequestMapping("/eurekaClientRemoteExample")
    String eurekaClientRemoteExample();

    @RequestMapping("/restTemplateRemoteMethodExample")
    String restTemplateRemoteMethodExample();
}
