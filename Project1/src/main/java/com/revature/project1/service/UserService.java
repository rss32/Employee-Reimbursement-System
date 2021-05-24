package com.revature.project1.service;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;

public interface UserService {
	
	public boolean validateUserCredentials(String username, String pass) throws BusinessException;

	public boolean validateUserInfo(User user) throws BusinessException;

}
