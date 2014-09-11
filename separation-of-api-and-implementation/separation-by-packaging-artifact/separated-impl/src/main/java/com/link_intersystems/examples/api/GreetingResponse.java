package com.link_intersystems.examples.api;

public class GreetingResponse {

	private String greeting;

	public GreetingResponse(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return greeting;
	}
}
