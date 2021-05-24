package com.revature.project1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public class FrontControllerServlet extends DefaultServlet {
	private static Logger log = LogManager.getRootLogger();
	private RequestHelper rh = new RequestHelper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		
		if (path.startsWith("/static/") || path.equals("/") || path.equals("/index.html")) {
			super.doGet(request, response);
		} else {
			rh.processGet(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		
		if (path.startsWith("/static/") || path.equals("/") || path.equals("/index.html")) {
			super.doGet(request, response);
		} else {
			rh.processPost(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		rh.processPut(request, response);
	}	

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			
			session.removeAttribute("user");
			session.invalidate();
		}
	}
}
