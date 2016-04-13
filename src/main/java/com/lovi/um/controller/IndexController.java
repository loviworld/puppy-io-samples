package com.lovi.um.controller;

import com.lovi.puppy.annotation.Controller;
import com.lovi.puppy.annotation.RequestMapping;
import com.lovi.puppy.exceptions.ServiceCallerException;
import com.lovi.puppy.future.HttpResponseResult;

@Controller
public class IndexController {

	@RequestMapping
	public void index(HttpResponseResult responseResult) throws ServiceCallerException{
		responseResult.complete("index");
	}
}
