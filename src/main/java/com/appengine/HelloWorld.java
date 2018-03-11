package com.appengine;
 
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.appengine.model.Webhook;
import com.appengine.utils.JSONTool;
 
@Controller
public class HelloWorld {
	
	private static final Logger log = Logger.getLogger(TestController.class.getName());

    @RequestMapping(value="/hi", method = RequestMethod.POST)
    public String hello(@RequestBody Webhook webhook) {
    	log.info(JSONTool.writeJSON(webhook));
        return "hello";
    }
}