package com.rudra.aks.service;

import java.util.List;
import java.util.Optional;

import com.rudra.aks.bo.UserBO;

public interface UserService {
	
	void createUser(UserBO user);
	UserBO findUser(int userid);
	List<UserBO>	findAllUser();
	Optional<UserBO>	findByUserName(String username);
}
