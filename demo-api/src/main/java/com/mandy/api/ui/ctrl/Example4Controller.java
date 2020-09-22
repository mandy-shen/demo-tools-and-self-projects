package com.mandy.api.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping(value = "eg4")
public class Example4Controller {
	
	static String TOPIC = "eg4";
	
	@GetMapping(value = "")
	public String index() {
		return "redirect:/eg4/one";
	}
	
	@GetMapping(value = "one")
	public String one(final Model model) {
		model.addAttribute("topic", TOPIC);
		return "/eg4/one";
	}
	
}
