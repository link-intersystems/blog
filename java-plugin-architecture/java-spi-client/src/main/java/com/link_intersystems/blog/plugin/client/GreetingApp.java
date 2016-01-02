package com.link_intersystems.blog.plugin.client;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.ServiceLoader;

import com.link_intersystems.blog.plugin.spi.GreetingService;
import com.link_intersystems.blog.plugin.spi.Person;
import com.link_intersystems.blog.plugin.spi.Gender;

public class GreetingApp {

	public static void main(String[] args) {
		ServiceLoader<GreetingService> greetingServiceLoader = ServiceLoader.load(GreetingService.class);

		Iterator<GreetingService> greetingServices = greetingServiceLoader.iterator();
		if (!greetingServices.hasNext()) {
			throw new IllegalStateException("No GreetingService provider found");
		}

		Person person = new MainArgsPersonAdapter(args);

		while (greetingServices.hasNext()) {
			GreetingService greetingService = greetingServices.next();
			String greeting = greetingService.greet(person);
			Class<? extends GreetingService> serviceImplClass = greetingService.getClass();
			String simpleServiceName = serviceImplClass.getSimpleName();
			String msg = MessageFormat.format("Greeting by provider {0}: {1}", simpleServiceName, greeting);
			System.out.println(msg);
		}
	}

	public static class MainArgsPersonAdapter implements Person {

		private String[] args;

		public MainArgsPersonAdapter(String[] args) {
			this.args = args;
			if (args.length < 2) {
				throw new IllegalArgumentException("You must specify at least a firstname and lastname");
			}
		}

		public String getFirstname() {
			return args[0];
		}

		public String getLastname() {
			return args[1];
		}

		public Gender getGender() {
			Gender sex = Gender.UNKNOWN;
			if (args.length > 2) {
				String sexArg = args[2].toUpperCase();
				sex = Gender.valueOf(sexArg);
			}
			return sex;
		}

	}
}
