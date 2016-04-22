package com.lovi.um.controller;

import com.lovi.puppy.annotation.Controller;
import com.lovi.puppy.annotation.RequestMapping;
import com.lovi.puppy.future.HttpResponseResult;
import com.lovi.puppy.web.Session;
import com.lovi.puppy.web.ViewAttribute;
import com.lovi.um.model.User;

@Controller
@RequestMapping("/users-dashboard")
public class DashBoardController {
	
	@RequestMapping
	public void loadDashBoardView(Session session, ViewAttribute viewAttribute, HttpResponseResult responseResult){
		User loggedUser = session.get("user", User.class);
		if(loggedUser != null){
			viewAttribute.put("loggedUser", loggedUser);
			responseResult.complete("users-dashboard");
		}else
			responseResult.complete("/");
	}

}