package com.revature.project1.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.project1.dao.UserDAO;
import com.revature.project1.dao.impl.UserDAOImpl;
import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.User;

public class AuthorizationDelegate {
	private static Logger log = LogManager.getRootLogger();
	private static UserDAO uDAO = new UserDAOImpl();

	public String authenticate(HttpServletRequest request, HttpServletResponse response, String path)
			throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		
		if (session == null) {

			session = request.getSession();
			session.setAttribute("user", new User());
			path = "login";

		} else if (session.getAttribute("user").equals(new User())) {

			if (request.getMethod().equals("POST")) {

				String username = request.getParameter("username");
				String password = request.getParameter("password");

				if (username.equals("") || password.equals("")) {
					path = "login-fail";
					log.info("login not successful");
				}

				
				User u = null;
				try {
					u = uDAO.getUserByCredentials(username, password);
					
					if (u == null) {
						path = "login-fail";
						log.info("login not successful");
					} else {
						session.removeAttribute("user");
						session.setAttribute("user", u);
					}

				} catch (BusinessException e) {

					log.info(e.getMessage());
					path = "login";
				}
			} else {
				path = "login";
			}
		}
		
		return path;
	}
}
