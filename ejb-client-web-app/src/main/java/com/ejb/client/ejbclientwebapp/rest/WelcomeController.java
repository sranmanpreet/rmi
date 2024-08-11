package com.ejb.client.ejbclientwebapp.rest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


interface HelloWorldBeanRemote {
	String getWelcomeMessage();
}

@RestController
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to EJB Client Web Application";
	}
	
	@GetMapping("/welcome/ejb")
	public String welcomeSecure() throws NoSuchMethodException {
		try {
			
			// Set JNDI properties
			Properties jndiProperties = new Properties();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		    jndiProperties.put("jboss.naming.client.ejb.context", true);
			
			InitialContext context = new InitialContext(jndiProperties);
			
//			Field[] fields = HelloWorldBeanRemote.class.getDeclaredFields();
//			for(Field field: fields) {
//				field.setAccessible(true);
//			}
//			Method[] methods = HelloWorldBeanRemote.class.getDeclaredMethods();
//			for(Method method: methods) {
//				method.setAccessible(true);
//			}
			
			Object helloWorld =  context
					.lookup("ejb:/MyFirstEJB//HelloWorldBean!com.ejb.poc.HelloWorldBeanRemote");
//			                 ejb:/MyFirstEJB/HelloWorldBean!com.ejb.poc.HelloWorldBeanRemote

			Method message = helloWorld.getClass().getDeclaredMethod("getWelcomeMessage");
			System.out.println("Welcome message from EJB: " + message);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Welcome to EJB Client Web Application (Secure)";
	}
}
