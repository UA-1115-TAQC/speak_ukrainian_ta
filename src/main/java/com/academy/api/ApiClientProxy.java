package com.academy.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiClientProxy implements InvocationHandler {
    private Object object;
    private static final Logger logger = LogManager.getLogger();

    public ApiClientProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        logger.info("'{}' method was invoked", method.getName());
        Object result = method.invoke(object, args);
        logger.info("'{}' method was completed", method.getName());
        return result;
    }

    public static Object getProxy(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), new ApiClientProxy(object));
    }
}
