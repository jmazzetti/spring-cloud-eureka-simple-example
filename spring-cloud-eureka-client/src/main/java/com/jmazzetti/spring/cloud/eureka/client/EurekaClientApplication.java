package com.jmazzetti.spring.cloud.eureka.client;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.discovery.shared.Application;
import com.netflix.appinfo.InstanceInfo;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication implements EurekaClientRemoteController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Override
    public String eurekaClientRemoteExample() {
        //Example how to retrieve from Eureka Server
        Application application = eurekaClient.getApplication("spring-cloud-eureka-client");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        System.out.println("InstanceInfo:" + instanceInfo);
        System.out.println("hostname:" + hostname);
        System.out.println("port:" + port);
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }




    @Override
    public String restTemplateRemoteMethodExample() {
        return "this is a test";
    }

}
