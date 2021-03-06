package com.lovi.um.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.lovi.puppy.annotation.Controller;
import com.lovi.puppy.annotation.ModelAttribute;
import com.lovi.puppy.annotation.RequestMapping;
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
	public void loadIndexView(ViewAttribute viewAttribute, Session session, HttpResponseResult responseResult) {
		User loggedUser = session.get("user", User.class);
		viewAttribute.put("user", new User());
		if (loggedUser != null) {
			responseResult.complete("/users-dashboard");
		} else
			responseResult.complete("index");
	}

	@RequestMapping(method = HttpMethod.POST)
	public void signIn(@ModelAttribute User user, 
							Session session,
							ViewAttribute viewAttribute, 
							HttpResponseResult responseResult) throws ServiceCallerException {

		Result<User> result = Result.create();
		FailResult failResult = FailResult.create();

		serviceCaller.call("UserService.findByUserIdAndPassword", result, user.getUserId(), user.getPassword());

		result.process(u -> {
			if (u != null){
				session.put("user", u);
				responseResult.complete("/");
			}else{
				viewAttribute.put("user", user);
				viewAttribute.put("message", "Wrong userId or Password");
				responseResult.complete("index");
			}
			

		} , failResult);

		failResult.setHandler(fail -> {
			viewAttribute.put("user", user);
			viewAttribute.put("message", fail.getMessage());
			responseResult.complete("index");
		});

	}

	@RequestMapping("/sign-up")
	public void loadSignUpView(HttpResponseResult responseResult) {
		responseResult.complete("sign-up");
	}

	@RequestMapping(value = "/sign-up", method = HttpMethod.POST)
	public void signUp(@ModelAttribute User user, 
							Session session,
							ViewAttribute viewAttribute, 
							HttpResponseResult responseResult)throws ServiceCallerException {

		Result<User> result = Result.create();
		FailResult failResult = FailResult.create();

		serviceCaller.call("UserService.insert", result, user);

		result.process(newUser -> {
			responseResult.complete("/");
		} , failResult);

		failResult.setHandler(fail -> {
			viewAttribute.put("user", user);
			viewAttribute.put("message", "duplicate_user");
			responseResult.complete("sign-up");
		});

	}
	
}
