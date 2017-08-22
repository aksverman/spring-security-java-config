package com.rudra.aks.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudra.aks.bo.UserBO;
import com.rudra.aks.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository	userRepository;
	
	
	@Override
	public void createUser(UserBO user) {
		UserBO newUser = userRepository.save(user);
		logger.info("User added : " + newUser);
	}
	
	@Override
	public UserBO findUser(int userid) {
		return userRepository.findOne(userid);
	}
	
	@Override
	public List<UserBO>	findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserBO> findByUserName(String username) {
		logger.info("Start : " + getClass().getName() + "findUserByName()");
		return userRepository.findOneByUsername(username);
	}
}
