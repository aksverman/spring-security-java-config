package com.rudra.aks.mvc.controller;

import javax.annotation.security.RolesAllowed;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/public")
public class PublicController {

	private static final Logger logger = Logger.getLogger(PublicController.class);
	
	/**
	 * @annotation secured check for roles access.
	 * It doesn't facilitates Spring Expression Language to be passed to authorization access.
	 * @return
	 */
	@Secured({ "ROLE_USER" })
	@RequestMapping("/secured")
	public String securityAnnotation() {
		logger.info("using @Secured with roles as user");
		return "public/welcome";
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/pre")
	public ModelAndView preAnnotation() {
		logger.info("Using @PreAuthorize with role as USER...");
		return new ModelAndView("public/welcome");
	}

	@PreAuthorize("#auth == authentication.name")
	@RequestMapping("/prewithparam/{auth}")
	public ModelAndView preWithParameters(@PathVariable("auth") String auth) {
		logger.info("Using @PreAuthorize with #paramteres...");
		logger.info("Authentication parameters : " + auth);
		return new ModelAndView("public/welcome");
	}
	
	@PostAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/post")
	public ModelAndView postAnnotation() {
		logger.info("Using @PostAuthorize with role as ADMIN...");
		return new ModelAndView("public/welcome");
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(principal, #username)")
	@RequestMapping("/precustom/{username}")
	public ModelAndView preWithRememberMe(@PathVariable String username) {
		logger.info("Using @PreAuthorize with remember me...");
		return new ModelAndView("public/welcome");
	}
	
	@RolesAllowed("ROLE_USER")
	@RequestMapping("/rolesallwoed")
	public ModelAndView rolesAllowed() {
		logger.info("Using @RolesAllowed...");
		return new ModelAndView("public/welcome");
	}
	
	
    @RequestMapping("/free")
	public String findById()  {
		logger.info("Using @RolesAllowed...");
		return "public/welcome";
	}
}
