package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserService;
import com.api.models.Response.ProfileResponse;
import com.api.models.request.LoginRequest;
import com.api.models.request.UpdateUserProfileRequest;

import io.restassured.response.Response;

public class UpdateUserProfileTest {
	
	@Test
	public void updateUserProfileTest() {
		
		LoginRequest loginRequest =  new LoginRequest("krishdsaviour", "Test@123");
		
		AuthService authService =  new AuthService();
		String token = authService.getToken(loginRequest);
		
		UpdateUserProfileRequest updateUserProfileRequest =  new UpdateUserProfileRequest.Builder().firstName("Karthick Rajesh")
				.lastName("DN").email("krishdsaviour@gmail.com").mobileNumber("7899877890").build();
		
		
		UserService userService = new UserService();
		Response response = userService.updateUserProfile(token ,updateUserProfileRequest);
		ProfileResponse profileResponse = response.as(ProfileResponse.class);
		
		 Assert.assertEquals(profileResponse.getFirstName(), updateUserProfileRequest.getFirstName());
		
		
		
	}

}
