package com.revature.project1.service.impl;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;
import com.revature.project1.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean validateUserCredentials(String username, String pass) throws BusinessException {

		if (username == null || username.replaceAll(" ", "").equals("") || username.length() > 50) {
			throw new BusinessException("username not valid.");
		}
		if (pass == null || pass.replaceAll(" ", "").equals("") || pass.length() > 50) {
			throw new BusinessException("password not valid.");
		}
		return true;
	}

	@Override
	public boolean validateUserInfo(User user) throws BusinessException {

		if (!user.getFirstName().matches("^[a-zA-Z]+([ -]?[a-zA-Z]){0,99}$") || user.getFirstName() == null) {
			throw new BusinessException("First name not valid.");
		}
		if (!user.getLastName().matches("^[a-zA-Z]+([ -]?[a-zA-Z]){0,99}$") || user.getLastName() == null) {
			throw new BusinessException("Last name not valid.");
		}
		if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || user.getEmail() == null) {
			throw new BusinessException("Email not valid.");
		}
		return true;
	}
}
