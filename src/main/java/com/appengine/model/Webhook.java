package com.appengine.model;

import org.springframework.beans.factory.annotation.Value;

public class Webhook {

	@Value("events")
	private Events[] events;

	public Events[] getEvents() {
		return events;
	}

	public void setEvents(Events[] events) {
		this.events = events;
	}
}
