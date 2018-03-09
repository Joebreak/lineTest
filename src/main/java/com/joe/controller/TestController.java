package com.joe.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.model.Webhook;
import com.joe.model.events;



@RestController
public class TestController {
	
	@RequestMapping(value="/")
	public String hello() {
		return "hello word";
	}
	
	@RequestMapping(value="/api/LineChat/TLJS")
	public void LineChat(@RequestBody Webhook webhook) {
		for (events event : webhook.getEvents()) {
			if ("text".equals(event.getMessage().getType())) {
				System.out.println(event.getMessage().getText());
			}
		}
	}
}
