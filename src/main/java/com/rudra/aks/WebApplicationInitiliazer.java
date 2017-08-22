package com.rudra.aks;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.rudra.aks.security.config.SecurityConfig;
import com.rudra.aks.session.SessionListener;

/**
 * 
 * @author Ankush.Verman
 *
 */

/*
 * To register dispatcher servlet and other configuration classes
 * and servlet mapping to access the controller using urls.
 */
public class WebApplicationInitiliazer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { DBConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}
}
