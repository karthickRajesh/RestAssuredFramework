package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		logRequest(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		logResponse(response);

		return response;
	}

	public void logRequest(FilterableRequestSpecification request) {

		logger.info("Request URI : " + request.getURI());
		logger.info("Request Header : " + request.getHeaders());
		logger.info("Request Methods" + request.getMethod());

		if (request.getBody() != null) {
			logger.info("Request body" + request.getBody());

		}

	}
	
	public void logResponse (Response response) {
		
		logger.info("Response status : " + response.getStatusCode());
		logger.info("Response body : " + response.getBody().asPrettyString());
		logger.info("Request Headers : " + response.headers());
	}

}
