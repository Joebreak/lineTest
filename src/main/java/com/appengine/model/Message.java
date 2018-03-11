package com.appengine.model;

public class Message {

	private String id;
	private String type;
	private String text;

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setText(String text) {
		this.text = text;
	}

}
