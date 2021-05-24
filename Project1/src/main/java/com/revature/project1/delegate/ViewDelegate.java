package com.revature.project1.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.project1.model.User;

public class ViewDelegate {
	public void resolveView(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");
		switch (path) {
		case "login":
			request.getRequestDispatcher("/static/login.html").forward(request, response);
			break;
		case "login-fail":
			request.getRequestDispatcher("/static/login-fail.html").forward(request, response);
			break;
		case "home":
			if (u.getUserRole() != null && u.getUserRole().startsWith("re")) {
				request.getRequestDispatcher("/static/employee-home.html").forward(request, response);
			} else {
				request.getRequestDispatcher("/static/manager-home.html").forward(request, response);
			}
			break;

		case "createReimbursement":
			request.getRequestDispatcher("/static/create-reimbursement.html").forward(request, response);
			break;

		case "profile":
			request.getRequestDispatcher("/static/update-user.html").forward(request, response);
			break;

		case "resolvedReimbursements":
			request.getRequestDispatcher("/static/resolved-reimbursements.html").forward(request, response);
			break;
			
		case "viewEmployees":
			request.getRequestDispatcher("/static/view-employees.html").forward(request, response);
			break;
			
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}
}
