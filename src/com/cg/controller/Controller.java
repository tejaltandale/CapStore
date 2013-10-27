package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.domain.AdvertisementMedia;
import com.cg.service.ServiceClass;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
	
	@Value("#{img['ipaddress2']}")	
	String ipaddress;
	@Autowired
	public ServiceClass sc;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showHome(ModelMap map){
		List<AdvertisementMedia> adlist=sc.getAdDetails();
		 List<String> mediapath=new ArrayList<>();
		 for(AdvertisementMedia am:adlist){
			 String newMediaPath=ipaddress+am.getMediaPath();
			 System.out.println(ipaddress);
			 mediapath.add(newMediaPath);
		 }
		map.put("list",adlist);
		map.put("list2",mediapath);
		return "Home";
	}	
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String showAdminLogin(){
		return "Admin_Login";
	}
	@RequestMapping(value="search",method=RequestMethod.GET)
	public String showSearch(){
		return "SearchResults";
	}
}
