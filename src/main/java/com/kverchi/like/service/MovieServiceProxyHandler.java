package com.kverchi.like.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MovieServiceProxyHandler implements InvocationHandler {

    MSIface orig;

    public MovieServiceProxyHandler(MSIface orig) {
        this.orig = orig;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("M a found");
        }
        return method.invoke(orig, args);
    }

}
