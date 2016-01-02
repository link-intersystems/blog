package com.link_intersystems.blog.singleton.byconstant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.SerializationUtils;

public class ConstantSingleton4Test {

	public static void main(String[] args) throws Exception {
		System.out
				.println("Well, you think your ConstantSingleton4 is really a singleton");

		System.out.println("The id of the INSTANCE object is "
				+ System.identityHashCode(ConstantSingleton4.INSTANCE));

		System.out.println("I will create another instance using reflection");
		Constructor<ConstantSingleton4> privateConstructor = ConstantSingleton4.class
				.getDeclaredConstructor();
		privateConstructor.setAccessible(true);
		try {
			privateConstructor.newInstance();
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			System.out
					.println("D'oh! An exception prevented me from creating a new instance: "
							+ targetException.getMessage());
		}

		System.out.println("Hmm, I will try one more option - Serialisation");

		ConstantSingleton4 clone = SerializationUtils
				.clone(ConstantSingleton4.INSTANCE);

		System.out.println("D'oh! Even serialization did not work. id = "
				+ System.identityHashCode(clone));

		System.out.println("I will give up");
	}
}