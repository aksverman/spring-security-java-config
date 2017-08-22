package com.rudra.aks.security.config;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.REQUIRED)//(Transactional.TxType.REQUIRED) //( propagation = Propagation.REQUIRED)
public class SecurityTokenRepositoryImpl implements PersistentTokenRepository {

	@Autowired
	EntityManagerFactory	entityFactory;
	
	public SecurityTokenRepositoryImpl() {
		logger.info("SecurityTokenRepository constructor");
	}

	private static final Logger logger = Logger.getLogger(SecurityTokenRepositoryImpl.class);
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		logger.info("Creating Token for user : " + token.getUsername());
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        
        EntityManager em = entityFactory.createEntityManager();
        Session session = em.unwrap(Session.class);
        String series = null;
        try {
        	Transaction transaction = session.beginTransaction();
        	series = (String) session.save(persistentLogin);
        	transaction.commit();
		} catch (Exception e) {
			logger.error(" Exception :" + e);
		}
        logger.info("New token created... : " + series);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
        logger.info("Updating Token for seriesId : " + series);
        EntityManager em = entityFactory.createEntityManager();
        Session session = em.unwrap(Session.class);
        
        PersistentLogin persistentLogin = (PersistentLogin) session.get(PersistentLogin.class, series);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        
        try {
        	Transaction transaction = session.beginTransaction();
        	session.update(persistentLogin);
        	transaction.commit();
		} catch (Exception e) {
			logger.error(" Exception :" + e);
		}    
        logger.info("token updated for series : " + series);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Fetch Token if any for seriesId : " + seriesId);
		EntityManager em = entityFactory.createEntityManager();
		Session unwrap = em.unwrap(Session.class);
        try {
            Criteria crit = unwrap.createCriteria(PersistentLogin.class);
            crit.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
 
            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            logger.info("Token not found...");
            return null;
        }
	}

	@Override
	public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : " + username);
		EntityManager em = entityFactory.createEntityManager();
		Session session = em.unwrap(Session.class);
		
        Criteria crit = session.createCriteria(PersistentLogin.class);//createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            logger.info("rememberMe was selected");
            try {
            	Transaction transaction = session.beginTransaction();
            	session.delete(persistentLogin);
            	transaction.commit();
    		} catch (Exception e) {
    			logger.error(" Exception :" + e);
    		}    
            logger.info("Token removed for username: " + username);
        }
	}

}
