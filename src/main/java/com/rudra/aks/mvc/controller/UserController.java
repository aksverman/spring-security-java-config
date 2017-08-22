package com.rudra.aks.mvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rudra.aks.bo.UserBO;
import com.rudra.aks.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService		userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	private static final String USER_LIST = "public/userslist";
	private final String CREATE_FORM = "registeruser";
	private final String WELCOME_FORM = "public/welcome";
	private final String RESOURCE_PAGE = "resources/resource";
	private final String ACCESS_DENIED = "public/accessdenied";
	
	@ModelAttribute("userbo")
	public UserBO defaultInstance() {
	    UserBO userbo = new UserBO();
	    return userbo;
	}
	
	@RequestMapping("/register")
	public String testController() {
		logger.info("Controller Accessed... ");
		return CREATE_FORM;
	}
	
	@RequestMapping("/create")
	public String getUser(@ModelAttribute("userbo") UserBO userbo) {
		userService.createUser(userbo);
		return WELCOME_FORM;
	}
	
	@RequestMapping("/show")
	public String showUserDetails() {
		logger.info("User access : ");
		return RESOURCE_PAGE;
	}
	
	@RequestMapping("/accessDenied")
	public String getAccessDeniedPage() {
		logger.info("User has requested unauthrozied resource...");
		return ACCESS_DENIED;
	}
	
	@RequestMapping("/listAll")
	public String listAllUsers(ModelMap	model) {
		logger.info("Listing all users : ");
		List<UserBO> usersList = userService.findAllUser();
		model.addAttribute("usersList", usersList);
		logger.info("No. of users fetched : " + usersList.size());
		return USER_LIST;
	}
	
	@RequestMapping("/login")
	public String getLoginPage() {
		logger.info("Login page accessed... ");
		/*try{
	        Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
	        Authentication result = authenticationManager.authenticate(request);
	        SecurityContextHolder.getContext().setAuthentication(result);
	    }
	    catch(Exception e){

	        e.printStackTrace();
	        return "incorrect";
	    }
		return "/main.xhtml"; */
		return WELCOME_FORM;
	}
}
