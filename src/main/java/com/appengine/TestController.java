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

import com.appengine.factory.ConnectionFactory;
import com.appengine.model.Event;
import com.appengine.model.MessagePushRequest;
import com.appengine.model.MessageReplyRequest;
import com.appengine.model.Webhook;
import com.appengine.utils.JSONTool;

import java.util.logging.Logger;

@SuppressWarnings("serial")
@WebServlet(name = "TestController", value = "/api/LineChat/TLJS")
public class TestController extends HttpServlet {
	private static final Logger log = Logger.getLogger(TestController.class.getName());

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

		StringBuffer sb = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"))) {
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (Exception e) {
			log.info("error : " + sb.toString());
		} finally {
		}
		Webhook webhook = JSONTool.readJSON(sb.toString(), Webhook.class);
		if (webhook == null) {
			log.info("webhook is null");
			log.info(sb.toString());
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
		writer.append("HI~!");
	}

}