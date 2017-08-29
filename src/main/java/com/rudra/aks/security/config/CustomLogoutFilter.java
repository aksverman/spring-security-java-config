package com.rudra.aks.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class CustomLogoutFilter /*extends OncePerRequestFilter*/ implements Filter {


	private static Logger logger = Logger.getLogger(CustomLogoutFilter.class);
	private static int count=0;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("CustomeLogoutFilter instantiated");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.info("Start : " + getClass().getName() + " : doFilter()");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Custom Filter.. has to invoked only once : " + ++count);
		filterChain.doFilter(request, response);
	}*/
	
	
	

}
