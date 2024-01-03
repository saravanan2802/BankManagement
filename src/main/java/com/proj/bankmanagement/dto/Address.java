package com.proj.bankmanagement.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Address {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int addressId;
	@NotBlank
	@NotNull
	private String street;
	@Min(value = 6000000000l, message = "Invalid Contact Number")
	@Max(value = 9999999999l, message = "Invalid Contact Number")
	private long Contact;
	@NotBlank
	@NotNull
	private String city;
	@NotBlank
	@NotNull
	private String state;
	@Min(value = 110001, message = "Invalid pincode")
	@Max(value = 999999, message = "Invalid pincode")
	private int pincode;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public long getContact() {
		return Contact;
	}

	public void setContact(long contact) {
		Contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
