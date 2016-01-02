package com.link_intersystems.blog.singleton.byconstant;

import java.io.Serializable;

public class ConstantSingleton3 implements Serializable {

	private static final long serialVersionUID = -4092252413936943233L;

	public static final ConstantSingleton3 INSTANCE = new ConstantSingleton3();

	private static boolean isCallerIsStaticInitializer() {
		StackTraceElement[] stackTrace = new Exception().getStackTrace();
		StackTraceElement shouldBeClinit = stackTrace[2];
		return "<clinit>".equals(shouldBeClinit.getMethodName())
				&& ConstantSingleton3.class.getName().equals(
						shouldBeClinit.getClassName());
	}

	private ConstantSingleton3() {
		if (!isCallerIsStaticInitializer()) {
			throw new RuntimeException(
					"You are not allowed to instantiate a singleton using reflection");
		}
	}
}
