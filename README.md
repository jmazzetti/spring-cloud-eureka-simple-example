### Relevant Articles:
- [Introduction to Spring Cloud Netflix – Eureka](http://www.jmazzetti.com)

This is a simple example with 3 projects associated:

- Eureka Server : Spring Boot server to enable services registration.

- Eureka Client : Spring Boot client to be discovered after being registered in Eureka Server. 

- Eureka Feign Client : Spring Boot client who discover the other Eureka Client in 2 differents ways:
	1. By Service Reference ("get-eurekaClientRemoteExample")
	2. By RestTemplate ("get-restTemplateRemoteMethodExample")
	

