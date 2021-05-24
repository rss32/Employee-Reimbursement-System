package com.revature.project1.dao;

import java.util.List;

import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;

public interface UserDAO {
	public User getUserByCredentials(String username, String password) throws BusinessException;

	public User getUserById(int id) throws BusinessException;

	public int updateUserInfo(User u) throws BusinessException;

	public List<User>getAllRegUsers() throws BusinessException;
}
