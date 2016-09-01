/**
 * This is a configuration class
 * 
 */

package com.jorge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jorge.service.UserService;

/**
 * Using the Java RMI, HTTP Invoker, Hessian, and REST
 * 
 * 		HTTP Invoker to interact with another Spring application
 * 		Java RMI to interact with another Java application not using Spring
 * 		Hessian to interact with another Java application not using Spring when you need to go over	proxies and firewalls
 * 		SOAP if you have to
 * 		REST for all other cases. REST is currently the most popular option; it's simple, flexible, and cross-platform
 *
 */
@Configuration // This declares it as a Spring configuration class
@EnableWebMvc // This enables Spring's ability to receive and process web requests. Necessary for interceptors too.
@ComponentScan(basePackages = { "com.jorge.controller" }) // This scans the com.jorge.controller package for Spring components

// @Import({ DatabaseConfig.class, SecurityConfig.class }) => //If you are using a Spring application without a 'ServletInitializer' class,
														      // you can include other configuration classes from your primary configuration class

public class AppConfig{

	 
	 /**
	  * Querying an existing HTTP Invoker service
	  * 
	  * We will configure a Spring web application that will be able to execute a method on an
	  * existing HTTP Invoker service
	  * 
	  */
	 @Bean(name="userService")
	 // HTTP Invoker CLIENT SIDE. Conflicts with 'public UserService userService()' method above. This one must be in client side and the other in server side.
	 public HttpInvokerProxyFactoryBean userService() {
		 HttpInvokerProxyFactoryBean factory = new HttpInvokerProxyFactoryBean();
		 
		 factory.setServiceUrl("http://localhost:8080/spring14_HTTPInvokerServer/userService"); // Points to server
		 factory.setServiceInterface(UserService.class); // Refers to UserService.java interface
		 
		 return factory;
	 }
	 
}