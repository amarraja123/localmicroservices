package com.webportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableBinding(PersonChannels.class)
@IntegrationComponentScan
@SpringBootApplication
public class PersonClientApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	Sampler customSampler(){
		return new AlwaysSampler();
	}

	public static void main(String[] args) {
		SpringApplication.run(PersonClientApplication.class, args);
	}
}

