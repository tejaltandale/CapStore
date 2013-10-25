package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.service.ServiceClass;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
	
	@Autowired
	public ServiceClass sc;

	@RequestMapping(method=RequestMethod.GET)
	public String showHome(){
		return "Home";
	}	
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String showAdminLogin(){
		return "Admin_Login";
	}
}
