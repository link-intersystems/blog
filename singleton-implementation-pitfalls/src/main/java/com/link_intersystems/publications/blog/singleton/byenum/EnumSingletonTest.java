package com.link_intersystems.publications.blog.singleton.byenum;

import java.lang.reflect.Constructor;

import org.apache.commons.lang3.SerializationUtils;

public class EnumSingletonTest {

	public static void main(String[] args) throws Exception {
		System.out
				.println("Well, you think your EnumSingleton is really a singleton");

		System.out.println("The id of the INSTANCE object is "
				+ System.identityHashCode(EnumSingleton.INSTANCE));

		System.out.println("I will create another instance using reflection");
		Constructor<EnumSingleton> privateConstructor = EnumSingleton.class
				.getDeclaredConstructor(String.class, int.class);
		privateConstructor.setAccessible(true);
		try {
			privateConstructor.newInstance();
		} catch (IllegalArgumentException e) {
			System.out
					.println("D'oh! An exception prevented me from creating a new instance: "
							+ e.getMessage());
		}

		System.out.println("Hmm, I will try one more option - Serialisation");

		EnumSingleton clone = SerializationUtils.clone(EnumSingleton.INSTANCE);

		System.out.println("D'oh! Even serialization did not work. id = "
				+ System.identityHashCode(clone));

		System.out.println("I give up");
	}
}