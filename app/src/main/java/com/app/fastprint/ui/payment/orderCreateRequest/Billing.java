package com.app.fastprint.ui.payment.orderCreateRequest;

import com.google.gson.annotations.SerializedName;

public class Billing{

	@SerializedName("country")
	private String country;

	@SerializedName("city")
	private String city;

	@SerializedName("phone")
	private String phone;

	@SerializedName("address_1")
	private String address1;

	@SerializedName("address_2")
	private String address2;

	@SerializedName("postcode")
	private String postcode;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("state")
	private String state;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	public String getCountry(){
		return country;
	}

	public String getCity(){
		return city;
	}

	public String getPhone(){
		return phone;
	}

	public String getAddress1(){
		return address1;
	}

	public String getAddress2(){
		return address2;
	}

	public String getPostcode(){
		return postcode;
	}

	public String getLastName(){
		return lastName;
	}

	public String getState(){
		return state;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getEmail(){
		return email;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}