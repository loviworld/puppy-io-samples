package com.lovi.um.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.lovi.puppy.annotation.Controller;
import com.lovi.puppy.annotation.RequestMapping;
import com.lovi.puppy.annotation.RequestParm;
import com.lovi.puppy.annotation.enums.HttpMethod;
import com.lovi.puppy.exceptions.ServiceCallerException;
import com.lovi.puppy.future.HttpResponseResult;
import com.lovi.puppy.message.FailResult;
import com.lovi.puppy.message.Result;
import com.lovi.puppy.message.ServiceCaller;
import com.lovi.puppy.web.Session;
import com.lovi.puppy.web.ViewAttribute;
import com.lovi.um.model.User;

@Controller
public class IndexController {

	@Autowired
	private ServiceCaller serviceCaller;
	
	@RequestMapping
	public void index(Session session, ViewAttribute viewAttribute, HttpResponseResult responseResult){
		User loggedUser = session.get("user", User.class);
		if(loggedUser != null){
			viewAttribute.put("loggedUser", loggedUser.getName());
			responseResult.complete("home");
		}else
			responseResult.complete("index");
	}
	
	@RequestMapping(method=HttpMethod.POST)
	public void signIn(	@RequestParm("userId") String userId, 
						@RequestParm("password") String password,
						Session session,
						HttpResponseResult responseResult) throws ServiceCallerException{
		
		Result<User> result = Result.create();
		FailResult failResult = FailResult.create();
		
		serviceCaller.call("UserService.findByUserIdAndPassword", result, userId, password);
		
		result.process(user->{
			if(user != null)
				session.put("user", user);
			
			responseResult.complete("/");
			
		}, failResult);
		
		failResult.setHandler(fail->{
			responseResult.complete("/");
		});
		
	}
}
