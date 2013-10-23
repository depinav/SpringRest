package com.victord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SiteNavigationContoller {

	@RequestMapping("/")
    public String home(ModelMap model) {
    	return "index";
    }
	
	@RequestMapping("/sencha")
	public String sencha(ModelMap model) {
		
		return "sencha";
	}
	
	@RequestMapping("/goBack")
	public View goBack(ModelMap model) {
		
		return new RedirectView("/SpringRest/person");
	}
}
