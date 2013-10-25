package com.cg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.service.ServiceClass;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	public ServiceClass sc;
	@RequestMapping(value="AskUserOrMerchant",method=RequestMethod.GET)
	public String ask(){
		return "Ask";
	}
	
	@RequestMapping(value="SignUpUser",method=RequestMethod.GET)
	public String signUpUser(){
		return "UserSignUp";
	}
	
	@RequestMapping(value="AddUser",method=RequestMethod.GET)	
	public String addUser(@RequestParam("n1")String userid,@RequestParam("n2")String fname,@RequestParam("n3")String lname,@RequestParam("n4")String pwd,@RequestParam("n5")String email,@RequestParam("n6")String sec_ques,@RequestParam("n7")String ans,@RequestParam("n8")String add){
		boolean flag=sc.addUser(userid,fname,lname,pwd,email,sec_ques,ans,add);
		String message=new String();
		if (flag) {
			message = "Home";
		} else {
			message = "UserNotAddedMessage";
		}
		return message;
	}	
	@RequestMapping(value="FetchUser",method=RequestMethod.GET)
	public String fetchUser(HttpServletRequest req){
		String userid=req.getParameter("userid");
		Long ts=Long.parseLong(req.getParameter("stamp"));
		System.out.println("aditya");
		boolean activated=sc.activateUser(userid,ts);
		String x=new String();
		if(activated){
			System.out.println("in activated");
			return x="Home";
		}else{
			x="NotActivated";
		}
		return x;
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String validateLogin(ModelMap map,HttpServletRequest request,
			@RequestParam("email") String u1,
			@RequestParam("password") String p1) {
		boolean valid = sc.validateLogin(request, u1, p1);		
		if (valid) {
			HttpSession ses = request.getSession();
			
			String s =(String) ses.getAttribute("userid");
			String ss=(String) ses.getAttribute("userName");
			map.put("username",ss);
			
			char a[];
			a=s.toCharArray();
			if (a[0] == 'C' && a[1] == '_') {

				return "/";
			} else if (a[0] == 'M' && a[1] == '_') {
				return "MerchantHome";
			}
		}
		return "Home";
	}
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		boolean valid = sc.logoutUser(request);
		if (valid) {
			return "Home";
		} else {
			HttpSession ses = request.getSession();
			String s = (String) ses.getAttribute("userid");
			char a[];
			a = s.toCharArray();
			if (a[0] == 'C' && a[1] == '_'){
				return "UserHome";
			} else if (a[0] == 'M' && a[1] == '_') {
				return "MerchantHome";

			}			return "Home";

		}

	}

}
