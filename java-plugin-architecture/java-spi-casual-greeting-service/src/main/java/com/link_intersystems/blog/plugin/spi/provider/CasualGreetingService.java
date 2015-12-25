package com.link_intersystems.blog.plugin.spi.provider;

import java.text.MessageFormat;

import com.link_intersystems.blog.plugin.spi.GreetingService;
import com.link_intersystems.blog.plugin.spi.Person;

public class CasualGreetingService implements GreetingService {

	public String greet(Person person) {
		String firstname = person.getFirstname();
		String greeting = MessageFormat.format("Hello {0}", firstname);
		return greeting;
	}

}
