package com.academy.apiClient.proxy;

import com.academy.api.ApiClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ApiClientProxy implements InvocationHandler{
    private Object targetObject;

    public ApiClientProxy(Object target) {
        this.targetObject = target;
    }

    public static Object createProxy(Object object) {
        ClassLoader classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, new ApiClientProxy(object));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method.getName() + ": START");
        Object result = method.invoke(targetObject, args);
        System.out.println("Method " + method.getName() + ": END");
        return result;
    }
}


