package com.api.base;

import io.restassured.response.Response;

public class UserService extends BaseService {
	
	private static final String BASE_PATH = "/api/users/";
	
	public Response getProfile(String token) {
		setAuthToken(token);
		return getRequest(BASE_PATH+"profile");
		
	}

}
