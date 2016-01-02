package com.link_intersystems.blog.singleton.byconstant;

import java.io.Serializable;

public class ConstantSingleton4 implements Serializable {

	private static final long serialVersionUID = -4092252413936943233L;

	public static final ConstantSingleton4 INSTANCE = new ConstantSingleton4();

	private static boolean isCallerIsStaticInitializer() {
		StackTraceElement[] stackTrace = new Exception().getStackTrace();
		StackTraceElement shouldBeClinit = stackTrace[2];
		return "<clinit>".equals(shouldBeClinit.getMethodName())
				&& ConstantSingleton4.class.getName().equals(
						shouldBeClinit.getClassName());
	}

	private ConstantSingleton4() {
		if (!isCallerIsStaticInitializer()) {
			throw new RuntimeException(
					"You are not allowed to instantiate a singleton using reflection");
		}
	}

	private Object readResolve() {
		return INSTANCE;
	}
}
