package com.ensate.app.studentapp.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserRequestModel {
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private String password;
	
	private String imageUrl;
	
	private String role;
	
}
