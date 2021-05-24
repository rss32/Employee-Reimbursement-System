package com.revature.project1.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.dao.UserDAO;
import com.revature.project1.dao.impl.UserDAOImpl;
import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;
import com.revature.project1.service.UserService;
import com.revature.project1.service.impl.UserServiceImpl;

public class UserDelegate {
	private static Logger log = LogManager.getRootLogger();
	private UserService uServ = new UserServiceImpl();
	private UserDAO uDAO = new UserDAOImpl();

	public void getUserParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		
		String idString = requestPath.replace("/api/users/", "");
		getUserById(request, response, idString);
	}

	private void getUserById(HttpServletRequest request, HttpServletResponse response, String idStr)
			throws IOException {
		User u = new User();
		try {
			int userId = Integer.parseInt(idStr);
			u = uDAO.getUserById(userId);
		} catch (NumberFormatException e) {
			log.info(e.getMessage());
			response.setStatus(400);
		} catch (BusinessException e) {
			log.info(e.getMessage());
			response.setStatus(500);
		}

		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(u));
	}

	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");

		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(u));
	}

	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");
		User newUser = new User();
		newUser.setId(u.getId());
		
		newUser.setFirstName(request.getParameter("firstName"));
		newUser.setLastName(request.getParameter("lastName"));
		newUser.setEmail(request.getParameter("email"));
		

		try {
			uServ.validateUserInfo(newUser);
			uDAO.updateUserInfo(newUser);
			u.setFirstName(newUser.getFirstName());
			u.setLastName(newUser.getLastName());
			u.setEmail(newUser.getEmail());
		} catch (BusinessException e) {
			log.info(e.getMessage());
			response.setStatus(400);
		}
	}

	public void getAllRegularUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<User> userList = null;
		try {
			userList = uDAO.getAllRegUsers();
		} catch (BusinessException e) {

			response.setStatus(500);
			log.info(e.getMessage());
			userList = new ArrayList<>();
		}

		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(userList));
	}
}
