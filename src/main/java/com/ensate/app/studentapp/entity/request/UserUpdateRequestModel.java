package com.ensate.app.studentapp.entity.request;

import lombok.Data;

@Data
public class UserUpdateRequestModel {
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private String imageUrl;
	
//	private String role;
	
}
