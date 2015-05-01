package com.link_intersystems.publications.blog.singleton.byconstant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstantSingleton2Test {

	public static void main(String[] args) throws Exception {
		System.out
				.println("Well, you think your ConstantSingleton2 is really a singleton");

		System.out.println("The id of the INSTANCE object is "
				+ System.identityHashCode(ConstantSingleton2.INSTANCE));

		System.out.println("I will create another instance using reflection");
		Constructor<ConstantSingleton2> privateConstructor = ConstantSingleton2.class
				.getDeclaredConstructor();
		privateConstructor.setAccessible(true);
		try {
			privateConstructor.newInstance();
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			System.out.println("D'oh! An exception prevented me from creating a new instance: " + targetException.getMessage());
		}
	}
}
