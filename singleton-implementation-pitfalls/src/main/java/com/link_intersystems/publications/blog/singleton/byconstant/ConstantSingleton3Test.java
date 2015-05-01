package com.link_intersystems.publications.blog.singleton.byconstant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.SerializationUtils;

public class ConstantSingleton3Test {

	public static void main(String[] args) throws Exception {
			System.out
					.println("Well, you think your ConstantSingleton3 is really a singleton");
	
			System.out.println("The id of the INSTANCE object is "
					+ System.identityHashCode(ConstantSingleton3.INSTANCE));
	
			System.out.println("I will create another instance using reflection");
			Constructor<ConstantSingleton3> privateConstructor = ConstantSingleton3.class
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
	
			ConstantSingleton3 clone = SerializationUtils
					.clone(ConstantSingleton3.INSTANCE);
	
			System.out
					.println("Gotcha! I created a clone using serialization. id = "
							+ System.identityHashCode(clone));
	}
}