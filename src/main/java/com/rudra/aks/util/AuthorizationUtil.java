package com.rudra.aks.util;

import com.rudra.aks.bo.CurrentUser;

public interface AuthorizationUtil {
	
	boolean isAuthorize(Object principal, String username);
}
