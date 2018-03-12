package com.appengine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageReplyRequest {

	@JsonProperty("replyToken")
	private String replyToken;
	@JsonProperty("messages")
	private List<Message> Messages;

	public static MessageReplyRequest toRequest(String replyToken, String stringMessage) {
		Message message = new Message();
		message.setType("text");
		message.setText(stringMessage);
		List<Message> messages = new ArrayList<>();
		messages.add(message);
		MessageReplyRequest request = new MessageReplyRequest();
		request.setMessages(Arrays.asList(message));
		request.setReplyToken(replyToken);
		return request;
	}
	
	public String getReplyToken() {
		return replyToken;
	}

	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}

	public List<Message> getMessages() {
		return Messages;
	}

	public void setMessages(List<Message> messages) {
		Messages = messages;
	}

}
