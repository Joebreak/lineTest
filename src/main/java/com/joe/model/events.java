package com.joe.model;

import org.springframework.beans.factory.annotation.Value;

public class events {

	private String replyToken;
	private String type;
	private Long timestamp;
	private source source;
	@Value("message")
	private Message message;

	public String getReplyToken() {
		return replyToken;
	}

	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public source getSource() {
		return source;
	}

	public void setSource(source source) {
		this.source = source;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
