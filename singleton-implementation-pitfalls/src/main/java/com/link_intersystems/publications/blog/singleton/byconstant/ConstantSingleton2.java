package com.link_intersystems.publications.blog.singleton.byconstant;

public class ConstantSingleton2 {

	public static final ConstantSingleton2 INSTANCE = new ConstantSingleton2();

	private static boolean isCallerIsStaticInitializer() {
		StackTraceElement[] stackTrace = new Exception().getStackTrace();
		StackTraceElement shouldBeClinit = stackTrace[2];
		return "<clinit>".equals(shouldBeClinit.getMethodName())
				&& ConstantSingleton2.class.getName().equals(
						shouldBeClinit.getClassName());
	}

	private ConstantSingleton2() {
		if (!isCallerIsStaticInitializer()) {
			throw new RuntimeException(
					"You are not allowed to instantiate a singleton using reflection");
		}
	}
}
