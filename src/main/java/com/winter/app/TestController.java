package com.winter.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
	
	@GetMapping("/")
	public String test() {
		//trace, debug, info, warn,error
		log.error("Error Message");
		log.warn("warn message");
		log.info("info message");
		log.debug("Debug message");
		log.trace("Trace Message");
		
		return "index";
	}

}
