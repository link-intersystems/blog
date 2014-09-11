package com.link_intersystems.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.link_intersystems.examples.service.GreetingRequest;
import com.link_intersystems.examples.service.GreetingResponse;
import com.link_intersystems.examples.service.GreetingService;

@Component
public class GreetingClientApplication implements Application {

	@Autowired
	private GreetingService greetingService;

	@Override
	public int run(String... args) {
		Integer personId = null;
		if (args.length > 0) {
			String personIdArg = args[0];
			personId = Integer.valueOf(personIdArg);
		}

		GreetingRequest greetingRequest = new GreetingRequest(personId);
		GreetingResponse greetingResponse = greetingService
				.sayHello(greetingRequest);

		System.out.println(greetingResponse.getGreeting());
		return 0;
	}
}
