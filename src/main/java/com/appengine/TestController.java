package com.appengine;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@SuppressWarnings("serial")
@WebServlet(name = "TestController", value = "/api/LineChat/TLJS")
public class TestController extends HttpServlet {
private static final Logger log = Logger.getLogger(TestController.class.getName());
    @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("application/json");
    doPost(request, response);
  }
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String user = request.getParameter("user");
		log.info(user);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// create HTML response
		PrintWriter writer = response.getWriter();
		writer.append("HI/n");
	}

}