package com.ensate.app.studentapp.entity.rest;

import lombok.Data;

@Data
public class UserRest {
	
	private Long id;

    private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private String role;
	
	private String imageUrl;
	
}
