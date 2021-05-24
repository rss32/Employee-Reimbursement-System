package com.revature.project1.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.dao.ReimbursementDAO;
import com.revature.project1.dao.impl.ReimbursementDAOImpl;
import com.revature.project1.exception.BusinessException;
import com.revature.project1.model.Reimbursement;
import com.revature.project1.model.User;
import com.revature.project1.service.ReimbursementService;
import com.revature.project1.service.impl.ReimbursementServiceImpl;

public class ReimbursementDelegate {
	private static Logger log = LogManager.getRootLogger();
	private static ReimbursementService rs = new ReimbursementServiceImpl();
	private static ReimbursementDAO rDAO = new ReimbursementDAOImpl();

	public void getUserParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		
		String finalPath = requestPath.replace("/api/reimbursements/", "");
		switch(finalPath) {
		case "pendingReimbursements/":
			getPendingReimbursementsByUserId(request,response);
			break;
		default:response.setStatus(404);
		}
	}
	private void getPendingReimbursementsByUserId(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Reimbursement> pendingList = null;
		try {
			int userId = Integer.parseInt(request.getParameter("user"));
			pendingList = rDAO.getPendingReimbursements(userId);
		}catch(NumberFormatException e) {
			response.setStatus(400);
			log.info(e.getMessage());
			pendingList = new ArrayList<>();
			
		}catch(BusinessException e) {
			response.setStatus(500);
			log.info(e.getMessage());
			pendingList = new ArrayList<>();
		}
		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(pendingList));
	}

	public void getUserPendingReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");
		List<Reimbursement> pendingList = null;
		try {
			pendingList = rDAO.getPendingReimbursements(u.getId());
		} catch (BusinessException e) {
			log.info(e.getMessage());
			pendingList = new ArrayList<>();
		}

		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(pendingList));
	}

	public void getUserApprovedReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");
		List<Reimbursement> apprReimbList = null;
		try {
			apprReimbList = rDAO.getApprovedReimbursements(u.getId());
		} catch (BusinessException e) {
			response.setStatus(500);
			log.info(e.getMessage());
			apprReimbList = new ArrayList<>();
		}		

		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(apprReimbList));
		writer.close();
	}

	public void getUserDeniedReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");
		List<Reimbursement> deniedList = null;
		try {
			deniedList = rDAO.getDeniedReimbursements(u.getId());
		} catch (BusinessException e) {
			response.setStatus(500);
			log.info(e.getMessage());
			deniedList = new ArrayList<>();
		}

		PrintWriter writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();
		writer.write(om.writeValueAsString(deniedList));
	}

	public void createPendingReimbursement(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");

		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String amount = request.getParameter("amount");

		Reimbursement r = new Reimbursement(new BigDecimal(amount), new Timestamp(System.currentTimeMillis()),
				description, type, "pending");
		r.setAuthor(u.getId());

		try {
			rs.validateReimbursement(r);
			rDAO.addPendingReimbursement(r);
		} catch (BusinessException e) {
			response.setStatus(500);
			log.info(e.getMessage());
		}
	}

	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession(false);
		User u = (User) s.getAttribute("user");
		try {
			int reimbId = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			Reimbursement r = new Reimbursement();
			r.setId(reimbId);
			r.setResolver(u.getId());
			r.setReimbStatus(status);
			rDAO.updateReimbursement(r);
			
		} catch (NumberFormatException e) {
			
			log.info(e.getMessage());
			response.setStatus(400);
		} catch (BusinessException e) {
			
			log.info(e.getMessage());
			response.setStatus(500);
		}		
	}

	public void getAllPendingReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		List<Reimbursement> pendingList = null;
		try {
			pendingList = rDAO.getAllPendingReimbursements();
		} catch (BusinessException e) {

			response.setStatus(500);
			log.info(e.getMessage());
			pendingList = new ArrayList<>();
		}

		PrintWriter writer;
		writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(pendingList));
	}
	
	public void getAllResolvedReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Reimbursement> resolvedList = null;
		try {
			resolvedList = rDAO.getAllResolvedReimbursements();
		} catch (BusinessException e) {

			response.setStatus(500);
			log.info(e.getMessage());
			resolvedList = new ArrayList<>();
		}

		PrintWriter writer;
		writer = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		writer.write(om.writeValueAsString(resolvedList));
	}	
}
