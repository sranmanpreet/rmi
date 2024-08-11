package com.ejb.poc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
public class HelloWorldBean implements HelloWorldBeanLocal, HelloWorldBeanRemote {

    /**
     * Default constructor. 
     */
    public HelloWorldBean() {
        // TODO Auto-generated constructor stub
//		Field[] fields = HelloWorldBean.class.getDeclaredFields();
//		for(Field field: fields) {
//			field.setAccessible(true);
//		}
//		Method[] methods = HelloWorldBean.class.getDeclaredMethods();
//		for(Method method: methods) {
//			method.setAccessible(true);
//		}
    }

	@Override
	public String getWelcomeMessage() {
		System.out.println("Call Received");
		return "Hello from EJBsss!";
	}

}
