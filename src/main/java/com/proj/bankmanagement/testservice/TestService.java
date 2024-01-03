package com.proj.bankmanagement.testservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class TestService {
	@Autowired
	Gson gson;
	@Autowired
	RestTemplate rsTemplate;
	@Autowired
	HttpHeaders headers;

	public ResponseEntity<String> redirectToOtherApi(HashMap<String, String> map) {

		String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

		headers.set("content-Type", "application/json");

		String jsonRequest = gson.toJson(map);

		HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

		ResponseEntity<String> response = rsTemplate.postForEntity(url, entity, String.class);

		return response;

	}

	public ResponseEntity<String> redirectToCreateCustomer(HashMap<String, String> map) {
		String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";

		HashMap<String, String> map2 = new HashMap<>();
		map2.put("login_id", "test@sunbasedata.com");
		map2.put("password", "Test@123");
		String bearerToken = redirectToOtherApi(map2).getBody();
		
		
		String[] str = bearerToken.split(":");
		char[] ch = str[1].toCharArray();
		String finalToken = ""; 
		for (char c : ch) {
			if(c!='"' && c!='}') {
				finalToken +=c;
			}
		}
		
		System.out.println(finalToken);

		headers.set("content-Type", "application/json");
		headers.set("Authorization", "Bearer "+ finalToken);
		String jsonRequest = gson.toJson(map);
		HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
		ResponseEntity<String> response = rsTemplate.postForEntity(url, entity, String.class);
		return response;
	}
}
