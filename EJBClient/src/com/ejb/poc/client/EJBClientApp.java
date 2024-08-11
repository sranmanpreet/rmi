package com.ejb.poc.client;

import java.lang.reflect.Method;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb.poc.HelloWorldBeanRemote;

public class EJBClientApp {

	public static void main(String[] args) {
		try {
			// Set JNDI properties
			Properties jndiProperties = new Properties();
//			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//			jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");
//			jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
//			jndiProperties.put("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
//			jndiProperties.put("java.naming.provider.url", "http-remoting://localhost:8080");
			
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:80/lbejb");
		    jndiProperties.put("jboss.naming.client.ejb.context", true);
			
			InitialContext context = new InitialContext(jndiProperties);
			
			HelloWorldBeanRemote helloWorld =  (HelloWorldBeanRemote)context
					.lookup("ejb:/MyFirstEJB//HelloWorldBean!com.ejb.poc.HelloWorldBeanRemote");
//			HelloWorldBeanRemote helloWorld = (HelloWorldBeanRemote) context.lookup("ejb:/MyFirstEJB/HelloWorldBean!com.ejb.poc.HelloWorldBean");
//			HelloWorldBean helloWorld = (HelloWorldBean) context.lookup("java:app/MyFirstEJB/HelloWorldBean!com.ejb.poc.HelloWorldBeanRemote");

			// Call the EJB method
			System.out.println(helloWorld.getWelcomeMessage());
//			Method message = helloWorld.getClass().getDeclaredMethod("getWelcomeMessage");
//			System.out.println(message.getReturnType());
//			System.out.println("Welcome message from EJB: " + message.invoke(helloWorld, null));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
