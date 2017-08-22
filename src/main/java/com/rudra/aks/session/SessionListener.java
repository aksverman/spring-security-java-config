package com.rudra.aks.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {

	private static final Logger logger = Logger.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("Session Created... with expire time 30 secs.");
		se.getSession().setMaxInactiveInterval(30);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Session expired !!! ");
	}

}
