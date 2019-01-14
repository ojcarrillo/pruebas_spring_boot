package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ClientGreetingService {

	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	public ClientGreetingService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	// invoke to greeting-service and return a Greeting object
	@HystrixCommand(fallbackMethod = "greetingDefault")
	public Greeting greeting(String name) {
		Greeting greeting = restTemplate.getForObject(serviceUrl + "/greeting/{name}", Greeting.class, name);
		return greeting;
	}

	public Greeting greetingDefault(String name) {
		return new Greeting("Hello World thanks to Circuit Breaker (Hystrix)");
	}

}
