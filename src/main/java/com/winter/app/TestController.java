package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winter.app.ajax.RestTempleTest;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
	
	@Autowired
	private RestTempleTest restTempleTest;

	
	@GetMapping("/")
	public String test() throws Exception {
		restTempleTest.flux();
		return "index";
	}
	
	@GetMapping("/expired")
	public String expried(Model model) {
		model.addAttribute("result", "로그아웃");
		model.addAttribute("path", "/");
		return "commons/result";
	}

}
