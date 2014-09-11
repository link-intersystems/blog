package com.link_intersystems.examples.service;

public class GreetingResponse {

	private String greeting;

	public GreetingResponse(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return greeting;
	}
}
