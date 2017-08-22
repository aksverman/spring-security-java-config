package com.rudra.aks.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


/*
 * This will register a servlet fitler proxy to enable security.
 * Each url will be then passed by security configuration only.
 * i.e., it will enable the @code SecurityConfig.java
 */
public class SecurityInit  extends AbstractSecurityWebApplicationInitializer{
	
	/*
	 * Not required if we are using Spring MVC configure to load applictionContext.
	 * Security configuration has to be loaded by mvc configuration.
	 * @see WebMvcCongi.java
	 */
	/*public SecurityInit() {
		super(SecurityConfig.class);
	}*/
}
