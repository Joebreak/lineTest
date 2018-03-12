package com.appengine.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.appengine.factory.ConnectionFactory;
import com.appengine.model.MessagePushRequest;
import com.appengine.model.MessageReplyRequest;
import com.appengine.model.Webhook;
import com.appengine.utils.JSONTool;
import com.appengine.model.Event;

@RestController
public class HelloWorld {

	private static final Logger logger = LogManager.getLogger(HelloWorld.class);


	@RequestMapping(value = "/api/LineChat/TLJS1", method = RequestMethod.POST)
	public void hello(@RequestBody Webhook webhook) {
		String sb = JSONTool.writeJSON(webhook);
		logger.info(sb);
		if (webhook == null) {
			logger.info("webhook is null");
			logger.info(sb);
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

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello1(@RequestBody Webhook webhook) {
		// log.info(JSONTool.writeJSON(webhook));
		return "hello~~~~~~~~~~~~~~~~";
	}
}