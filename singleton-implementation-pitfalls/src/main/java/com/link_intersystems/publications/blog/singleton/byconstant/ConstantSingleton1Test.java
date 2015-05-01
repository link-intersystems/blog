package com.link_intersystems.publications.blog.singleton.byconstant;

import java.lang.reflect.Constructor;

public class ConstantSingleton1Test {

	public static void main(String[] args) throws Exception {
		System.out
				.println("Well, you think your ConstantSingleton1 is really a singleton");

		System.out.println("The id of the INSTANCE object is "
				+ System.identityHashCode(ConstantSingleton1.INSTANCE));

		System.out.println("I will create another instance using reflection");
		Constructor<ConstantSingleton1> privateConstructor = ConstantSingleton1.class
				.getDeclaredConstructor();
		privateConstructor.setAccessible(true);
		ConstantSingleton1 newInstance = privateConstructor.newInstance();

		System.out
				.println("Gotcha! Just created a new instance using relfection. id = "
						+ System.identityHashCode(newInstance));
	}
}
