package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.Response.LoginResponse;
import com.api.models.request.LoginRequest;

import io.restassured.response.Response;

public class LoginTest {

	@Test
	public void loginTest() {

		LoginRequest loginRequest = new LoginRequest("krishdsaviour", "Test@123");

		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.print(response.asPrettyString());

		System.out.println("token is :" + loginResponse.getToken());
		System.out.println("type is :" + loginResponse.getType());
		System.out.println("id is :" + loginResponse.getId());
		System.out.println("username is :" + loginResponse.getUsername());
		System.out.println("email is :" + loginResponse.getEmail());
		System.out.println("roles is :" + loginResponse.getRoles());

	}

}
