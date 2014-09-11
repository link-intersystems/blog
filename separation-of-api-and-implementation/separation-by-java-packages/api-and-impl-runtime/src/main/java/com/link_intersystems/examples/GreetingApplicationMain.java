package com.link_intersystems.examples;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GreetingApplicationMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.scan("com.link_intersystems.examples");
			ctx.refresh();

			Application application = ctx.getBean(Application.class);
			application.run("1");
		} finally {
			ctx.close();
		}
	}
}
