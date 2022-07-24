package com.muskan.shop;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.muskan.shop.model.ApplicationError;

import java.util.*;
@WebServlet("/ErrorHandler")
public class ErrorHandlerServlet extends HttpServlet {





	// Method to handle GET method request.
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
ApplicationError applicationError=new ApplicationError();
applicationError.setMessage("Something went wrong don't worry we will resolve it soon for any query please write mail to sahumukesh553@gmail.com");
		// Analyze the servlet exception       
		Throwable throwable = (Throwable)
				request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer)
				request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String)
				request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String)
				request.getAttribute("javax.servlet.error.request_uri");
applicationError.setThrowable(throwable);
applicationError.setStatusCode(statusCode);
applicationError.setServletName(servletName);
applicationError.setRequestUri(requestUri);
		if (servletName == null) {
			servletName = "Unknown";
			applicationError.setServletName(servletName);
		}
		
		if (requestUri == null) {
			requestUri = "Unknown";
			applicationError.setRequestUri(requestUri);
		}

	request.setAttribute("applicationError", applicationError);
	request.getRequestDispatcher("error.jsp").forward(request, response);
		
		
	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}

