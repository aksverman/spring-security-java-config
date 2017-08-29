package com.rudra.aks.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "customAuthorizationUtil")
public class AuthorizationUtilImpl implements AuthorizationUtil {

	private static Logger logger = Logger.getLogger(AuthorizationUtilImpl.class);
	
	
	public boolean isAuthorize(Object principal, String username) {
		logger.info("Start : " + getClass().getName() + " : isAuthorize()");
		
		return (principal != null && username != null);
	}
}
