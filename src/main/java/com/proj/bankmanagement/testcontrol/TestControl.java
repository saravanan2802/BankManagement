package com.proj.bankmanagement.testcontrol;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.proj.bankmanagement.testservice.TestService;

@RestController
@RequestMapping("/test")
public class TestControl {
	@Autowired
	TestService testService;
	@Autowired
	RestTemplate rsTemplate;

	@GetMapping
	public ResponseEntity<String> getBearer(@RequestBody HashMap<String, String> map) {
		return testService.redirectToOtherApi(map);
	}

	@PostMapping
	public ResponseEntity<String> createCustomer(@RequestBody HashMap<String, String> map) {
		return testService.redirectToCreateCustomer(map);
	}

	@PutMapping
	public String getWeather(@RequestParam String city) {
		String url = "http://api.weatherstack.com/current?access_key=6f1bdf0ac1e951d8839e9b92144362dd&query=" + city;

		String weatherData = rsTemplate.getForObject(url, String.class);

		return weatherData;
	}
}
