package com.proj.bankmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean
	public Gson getGson() {
		return new Gson();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public HttpHeaders getHttpHeader() {
		return new HttpHeaders();
	}
}
