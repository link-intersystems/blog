package com.link_intersystems.publications.blog.classloader_pitfalls;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Assert;
import org.junit.Test;

public class ClassCastExceptionTest {

	/**
	 * This test shows that a class loaded by one class loader is not equal to
	 * the same class loaded by another class loader.
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test(expected = ClassCastException.class)
	@SuppressWarnings("unused")
	public void classCastExceptionWithDifferentClassLoaders()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// Calculator is just a test class that I created
		URL[] urls = getClassLoaderClasspath(Calculator.class);

		URLClassLoader classLoader1 = new URLClassLoader(urls, null);
		URLClassLoader classLoader2 = new URLClassLoader(urls, null);

		try {
			String canonicalName = Calculator.class.getCanonicalName();
			Class<?> calcClass1 = classLoader1.loadClass(canonicalName);
			Class<?> calcClass2 = classLoader2.loadClass(canonicalName);

			// The classes are not equal, because they are loaded by different
			// class
			// loaders.
			Assert.assertFalse(calcClass1.equals(calcClass2));

			Object calcInstance1 = calcClass1.newInstance();
			Object calcInstance2 = calcClass2.newInstance();

			// As the test defines we expect a ClassCastException, because an
			// instance of calcClass2 can not be casted to calcClass1
			calcClass1.cast(calcInstance2);
		} finally {
			closeQuietly(classLoader1);
			closeQuietly(classLoader2);
		}
	}

	private void closeQuietly(URLClassLoader classLoader) {
		try {
			classLoader.close();
		} catch (IOException e) {
		}
	}

	private URL[] getClassLoaderClasspath(Class<?> clazz) {
		ClassLoader classLoader = clazz.getClassLoader();
		Assert.assertTrue(classLoader instanceof URLClassLoader);
		URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
		URL[] urls = urlClassLoader.getURLs();
		return urls;
	}
}
