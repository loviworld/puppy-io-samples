package com.lovi.um.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.lovi.puppy.annotation.Controller;
import com.lovi.puppy.annotation.ModelAttribute;
import com.lovi.puppy.annotation.RequestMapping;
import com.lovi.puppy.annotation.enums.HttpMethod;
import com.lovi.puppy.exceptions.ServiceCallerException;
import com.lovi.puppy.future.HttpResponseResult;
import com.lovi.puppy.message.ServiceCaller;
import com.lovi.puppy.web.Session;
import com.lovi.puppy.web.ViewAttribute;
import com.lovi.um.model.User;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {
	
	@Autowired
	private ServiceCaller serviceCaller;
	
	@RequestMapping
	public void index(Session session, ViewAttribute viewAttribute, HttpResponseResult responseResult){
		responseResult.complete("sign-up");
	}
	
	@RequestMapping(method=HttpMethod.POST)
	public void signUp(	@ModelAttribute User user,
						Session session,
						HttpResponseResult responseResult) throws ServiceCallerException{
		
		serviceCaller.call("UserService.insert", user);
		responseResult.complete("/");
		
	}

}
