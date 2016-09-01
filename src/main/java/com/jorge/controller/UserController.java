package com.jorge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jorge.model.User;
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

@Controller
// CLIENT SIDE
public class UserController {
	
	@Autowired
	private UserService userService; // Refers to UserService interface
	
	/**
	 * HTTP Invoker service:
	 * In the UserController class, the autowired UserService object is instantiated behind the scenes by
	 * Spring. It's actually an HttpInvokerProxyFactoryBean , which will delegate the execution of the
	 * findAll() method to the HTTP Invoker service located at
	 * http://localhost:8080/http_invoker_server/userService
	 * 
	 * Try:
	 * 		http://localhost:8080/spring14_HTTPInvokerClient/user_list
	 * 
	 * It returns the user list
	 * 
	 */
	@RequestMapping("user_list")
	@ResponseBody
	public String userList() {
		String res = "";
		
		List<User> userList = userService.findAll(); // RMI Client: Execute the findAll() method of the UserService object
		
		for(User u : userList){
			System.out.println("Name: " + u.getName());
			System.out.println("Age: " + u.getAge() + "\n");
			
			res += "Name: <b>" + u.getName() + "</b><br/>" + "Age: <b>" + u.getAge() + "</b><br/><br/>";
		}
		
		return res;
	}
}
