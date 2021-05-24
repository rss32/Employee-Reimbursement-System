package com.revature.project1.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.dao.UserDAO;
import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;
import com.revature.project1.util.dbutil.DBConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getUserByCredentials(String username, String password) throws BusinessException {

		User u = null;
		try (Connection conn = DBConnectionUtil.getConnection()) {

			String sql = "select u.ers_users_id,u.user_first_name, u.user_last_name,u.user_email ,ur.user_role "
					+ "from ers.ers_users u join ers.ers_user_roles ur on u.user_role_id = ur.ers_user_role_id "
					+ "where u.ers_username = ? and u.ers_password = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet results = pstmt.executeQuery();
			if (results.next()) {
				u = new User();
				u.setId(results.getInt("ers_users_id"));
				u.setUsername(username);
				// u.setPassword(password);
				u.setFirstName(results.getString("user_first_name"));
				u.setLastName(results.getString("user_last_name"));
				u.setEmail(results.getString("user_email"));
				u.setUserRole(results.getString("user_role"));
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return u;
	}

	@Override
	public User getUserById(int id) throws BusinessException {
		User u = null;
		try (Connection conn = DBConnectionUtil.getConnection()) {

			String sql = "select u.ers_username, u.user_first_name, u.user_last_name,u.user_email , "
					+ "ur.user_role from ers.ers_users u 	join ers.ers_user_roles ur  "
					+ "on u.user_role_id = ur.ers_user_role_id where u.ers_users_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet results = pstmt.executeQuery();
			if (results.next()) {
				u = new User();
				u.setId(id);
				u.setUsername(results.getString("ers_username"));				
				u.setFirstName(results.getString("user_first_name"));
				u.setLastName(results.getString("user_last_name"));
				u.setEmail(results.getString("user_email"));
				u.setUserRole(results.getString("user_role"));
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return u;
	}

	@Override
	public int updateUserInfo(User u) throws BusinessException {
		int updatedRecords = 0;
		try (Connection conn = DBConnectionUtil.getConnection()) {
			
			String sql = "update ers.ers_users set user_first_name = ?, user_last_name= ?, user_email= ? "
					+ "where ers_users_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getFirstName());
			pstmt.setString(2, u.getLastName());
			pstmt.setString(3, u.getEmail());
			pstmt.setInt(4, u.getId());

			updatedRecords = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return updatedRecords;
	}

	@Override
	public List<User> getAllRegUsers() throws BusinessException {

		//re1- regular employees
		//fm1 - finance manager
		List<User> userList = new ArrayList<>();
		User user = null;
		try (Connection conn = DBConnectionUtil.getConnection()) {
			String sql = "select u.ers_users_id, u.ers_username, u.user_first_name, u.user_last_name, u.user_email, "
					+ "ur.user_role from ers.ers_users u join ers.ers_user_roles ur on u.user_role_id = ur.ers_user_role_id "
					+ "where ur.user_role ='re1'";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				user = new User();

				user.setId(results.getInt("ers_users_id"));
				user.setUsername(results.getString("ers_username"));
				user.setFirstName(results.getString("user_first_name"));
				user.setLastName(results.getString("user_last_name"));
				user.setEmail(results.getString("user_email"));
				user.setUserRole(results.getString("user_role"));

				userList.add(user);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new BusinessException("Contact System Admin. " + e.getMessage());
		}
		return userList;
	}

}
