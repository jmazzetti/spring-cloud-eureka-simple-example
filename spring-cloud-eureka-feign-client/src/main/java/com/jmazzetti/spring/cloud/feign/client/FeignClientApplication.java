package com.jmazzetti.spring.cloud.feign.client;


import com.jmazzetti.spring.cloud.eureka.client.EurekaClientApplication;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Controller
public class FeignClientApplication {

    // This autowired is to Discover throw Feign service
    @Autowired
    private EurekaClientApplication eurekaClientApplication;

    // This autowired is to Discover throw RestTemplate
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @RequestMapping("/get-eurekaClientRemoteExample")
    public String greeting(Model model) {
        model.addAttribute("remote-client-response", eurekaClientApplication.eurekaClientRemoteExample());
        return "remote-client-response-view";
    }


    @RequestMapping("/get-restTemplateRemoteMethodExample")
    public String getGreetingWithRestTemplate(Model model){
        try{
            Application application = eurekaClient.getApplication("spring-cloud-eureka-client");
            InstanceInfo instance = application.getInstances().get(0);
            URI uri = UriComponentsBuilder.fromUriString(instance.getAppName()).build().toUri();
            String response = restTemplate.getForObject("http://" + instance.getHostName() +":" + instance.getPort()+"/restTemplateRemoteMethodExample", String.class);
            System.out.println("Response is:" + response);
            model.addAttribute("remote-client-response", response);
            return "remote-client-response-view";
        }catch (Exception e){
            e.printStackTrace();
        }


        return "greeting-view";
    }


    @Primary
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
