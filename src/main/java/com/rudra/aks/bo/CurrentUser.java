package com.rudra.aks.bo;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserBO user;

    public CurrentUser(UserBO user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public UserBO getUser() {
        return user;
    }

    public int getUserId() {
        return user.getUserid();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
