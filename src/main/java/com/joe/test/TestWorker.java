package com.joe.test;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TestWorker {

	@PostConstruct
	private void init() {
		System.out.println("hello word");
	}
}
