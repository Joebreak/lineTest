package com.appengine.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessagePushRequest {

	private String to;
	@JsonProperty("messages")
	private List<Message> messages;


	public static MessagePushRequest toRequest(String text) {
		Message messages = new Message();
		messages.setType("text");
		messages.setText(text);
		MessagePushRequest request = new MessagePushRequest();
		request.setTo("U47ad2aed1c9118b0ea35cce8713120c2");
		request.setMessages(Arrays.asList(messages));
		return request;
	}
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
