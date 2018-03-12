package com.appengine.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.IOUtils;

import com.appengine.factory.ConnectionFactory;
import com.appengine.model.Event;
import com.appengine.model.MessagePushRequest;
import com.appengine.model.MessageReplyRequest;
import com.appengine.model.Webhook;
import com.appengine.utils.JSONTool;


@SuppressWarnings("serial")
@WebServlet(value = "/api/LineChat/TLJS")
public class TestController extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(TestController.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.append("/api/LineChat/TLJS11111");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String requestString = IOUtils.toString(request.getInputStream());
		writer.append(requestString);
		logger.info(requestString);
		Webhook webhook = JSONTool.readJSON(requestString, Webhook.class);
		if (webhook == null) {
			logger.info("webhook is null");
			logger.info(requestString.toString());
			return;
		}
		for (Event event : webhook.getEvents()) {
			if ("message".equals(event.getType()) && "text".equals(event.getMessage().getType())) {
				String replyToken = event.getReplyToken();
				String message = event.getMessage().getText();
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						ConnectionFactory connection = new ConnectionFactory();
						connection.sendLineBotReply(MessageReplyRequest.toRequest(replyToken, message));
						if (event.getSource() != null
								&& !"U47ad2aed1c9118b0ea35cce8713120c2".equals(event.getSource().getUserId())) {
							connection.sendLineBotPush(MessagePushRequest.toRequest(message));
						}
					}
				});
				thread.setDaemon(true);
				thread.start();
			}
		}
	}

}