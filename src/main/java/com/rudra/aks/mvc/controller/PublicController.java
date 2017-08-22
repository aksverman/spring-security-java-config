package com.rudra.aks.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/public")
public class PublicController {

	private static final Logger logger = Logger.getLogger(PublicController.class);
	
	@RequestMapping("/access")
	public String publicAccess() {
		logger.info("Unsecured Access to Controller");
		return "public/welcome";
	}
}
