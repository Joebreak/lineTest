package com.appengine.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class Webhook {

	@Value("events")
	private List<Event> events;

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
