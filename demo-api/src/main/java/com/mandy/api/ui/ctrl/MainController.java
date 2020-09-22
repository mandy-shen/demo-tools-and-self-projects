package com.mandy.api.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping(value = "/")
public class MainController {
	
	@GetMapping(value = "")
	public String root() {
		return "redirect:/main/index";
	}
	
	@GetMapping(value = "/main/index")
	public String index() {
		return "/main/index";
	}
	
}
