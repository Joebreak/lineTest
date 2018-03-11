package com.appengine;
 
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appengine.model.Webhook;
import com.appengine.utils.JSONTool;
 
@RestController
public class HelloWorld {
	
	private static final Logger log = Logger.getLogger(HelloWorld.class.getName());

    @RequestMapping(value="/hi", method = RequestMethod.POST)
    public void hello(@RequestBody Webhook webhook) {
    	log.info(JSONTool.writeJSON(webhook));
        //return "hello";
    }
    
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello1(@RequestBody Webhook webhook) {
    	//log.info(JSONTool.writeJSON(webhook));
        return "hello~~~~~~~~~~~~~~~~";
    }
}