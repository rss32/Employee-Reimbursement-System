package com.revature.project1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1.delegate.AuthorizationDelegate;
import com.revature.project1.delegate.ReimbursementDelegate;
import com.revature.project1.delegate.UserDelegate;
import com.revature.project1.delegate.ViewDelegate;

public class RequestHelper {

	private AuthorizationDelegate ad = new AuthorizationDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();	
	private UserDelegate ud = new UserDelegate();
	private ViewDelegate vd = new ViewDelegate();	

	public void processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder path = new StringBuilder(request.getRequestURI());
		path.replace(0, request.getContextPath().length() + 1, "");
		

		// routes the user to login page if not valid user
		String path1 = ad.authenticate(request, response, path.toString());
		
		switch (path1) {
		case "api/user":
			ud.getUserInfo(request, response);
			break;
		case "api/user/pendingReimbursements":
			rd.getUserPendingReimbursement(request, response);
			break;

		case "api/user/approvedReimbursements":
			rd.getUserApprovedReimbursement(request, response);
			break;

		case "api/user/deniedReimbursements":
			rd.getUserDeniedReimbursement(request, response);
			break;

		case "api/reimbursement/createReimbrusement":
			rd.createPendingReimbursement(request, response);
			break;
		case "api/reimbursement/updateReimbursement":
			rd.updateReimbursement(request, response);
			break;
		case "api/user/updateUser":
			ud.updateUserInfo(request, response);
			break;
		case "api/user/allRegularUsers":
			ud.getAllRegularUsers(request, response);
			break;

		case "api/reimbursement/allPendingReimbursements":
			rd.getAllPendingReimbursements(request, response);
			break;
		case "api/reimbursement/allResolvedReimbursements":
			rd.getAllResolvedReimbursements(request, response);
			break;
		default:
			if (path1.startsWith("api/users/")) {
				ud.getUserParam(request, response);
			} else if (path1.startsWith("api/reimbursements/")) {
				rd.getUserParam(request, response);
			} else {
				vd.resolveView(request, response, path1);
			}
		}
	}

	public void processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processGet(request, response);
	}

	public void processPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processGet(request, response);
	}
}
