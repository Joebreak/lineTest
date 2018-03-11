package com.appengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
@WebServlet(name = "TestController", value = "/api/LineChat/TLJS")
public class TestController extends HttpServlet {
	private static final Logger log = Logger.getLogger(TestController.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.append("HI~");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// create HTML response
		PrintWriter writer = response.getWriter();
		writer.append("HI/n");

		StringBuffer sb = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"))) {
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "error", e);
		} finally {
		}

		log.info(sb.toString());
		// log.info(JSONTool.writeJSON(writer));
	}

}