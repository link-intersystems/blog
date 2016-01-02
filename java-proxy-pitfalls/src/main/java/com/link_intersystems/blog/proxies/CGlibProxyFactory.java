package com.link_intersystems.blog.proxies;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class CGlibProxyFactory {

	@SuppressWarnings("unchecked")
	public <T> T createProxy(T targetObject, Callback callback) {
		if (targetObject == null) {
			throw new IllegalArgumentException("targetObject must not be null");
		}
		if (callback == null) {
			throw new IllegalArgumentException("callback must not be null");
		}
		Class<?> targetType = targetObject.getClass();
		return (T) Enhancer.create(targetType, callback);
//		Class<?> superclass = targetType.getSuperclass();
//		List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(targetType);
//		Class<?>[] allInterfacesAsArray = (Class<?>[]) allInterfaces.toArray(new Class<?>[allInterfaces.size()]);
//		Enhancer enhancer = new Enhancer();
//		enhancer.setSuperclass(superclass);
//		enhancer.setInterfaces(allInterfacesAsArray);
//		enhancer.setCallback(callback);
//		enhancer.setUseFactory(true);
//
//		Object proxyObject = enhancer.create();
//		return (T) proxyObject;
	}
}
