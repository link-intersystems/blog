package com.link_intersystems.blog.proxies;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.InvocationHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.link_intersystems.blog.proxies.CGlibProxyFactory;
import com.link_intersystems.blog.proxies.Person;

public class CGlibProxyEqualsTest {

	private CGlibProxyFactory cGlibProxyFactory;

	private Person person1;

	private Person person2;

	private Person personProxy1;

	@Before
	public void createProxyAndTargetObjects() {
		cGlibProxyFactory = new CGlibProxyFactory();
		person1 = new Person("René", "Link");
		person2 = new Person("René", "Link");

		Assert.assertEquals("persons with equal firstname and lastname should be equal", person1, person2);

		personProxy1 = createProxy(person1);
	}

	private Person createProxy(Person person) {
		return cGlibProxyFactory.createProxy(person, new PassthroughInvocationHandler(person));
	}

	@Test
	public void realObjectEqualsProxy() {
		boolean isEqual = person1.equals(person1);
		Assert.assertTrue(isEqual);

		isEqual = person1.equals(personProxy1);
		Assert.assertFalse(isEqual);

		isEqual = personProxy1.equals(personProxy1);
		Assert.assertFalse(isEqual);

		isEqual = personProxy1.equals(person1);
		Assert.assertTrue(isEqual);
	}

	private static class PassthroughInvocationHandler implements InvocationHandler {

		private final Object target;

		public PassthroughInvocationHandler(Object target) {
			this.target = target;
		}

		public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
			Object result = method.invoke(target, args);
			return result;
		}

	}
}
