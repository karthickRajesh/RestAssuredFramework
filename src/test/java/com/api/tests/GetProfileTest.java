package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserService;
import com.api.models.Response.ProfileResponse;
import com.api.models.request.LoginRequest;

import io.restassured.response.Response;

public class GetProfileTest {

	@Test
	public void getProfileTest() {

		LoginRequest loginRequest = new LoginRequest("krishdsaviour", "Test@123");

		AuthService authService = new AuthService();
		String token = authService.getToken(loginRequest);

		UserService userService = new UserService();
		Response response = userService.getProfile(token);
		ProfileResponse profileResponse = response.as(ProfileResponse.class);

		System.out.println("id is : " + profileResponse.getId());
		System.out.println("username is: " + profileResponse.getUsername());
		System.out.println("email is: " + profileResponse.getEmail());
		System.out.println("firstName is: " + profileResponse.getFirstName());
		System.out.println("lastName is: " + profileResponse.getLastName());
		System.out.println("mobileNumber is: " + profileResponse.getMobileNumber());
	}

}
