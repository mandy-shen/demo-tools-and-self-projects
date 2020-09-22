package com.mandy.api.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping(value = "eg0")
public class Example0Controller {
	
	static String TOPIC = "eg0";
	
	@GetMapping(value = "")
	public String index() {
		return "redirect:/eg0/one";
	}

	@GetMapping(value = "one")
	public String one(final Model model) {
		model.addAttribute("topic", TOPIC);
		return "/eg0/one";
	}
	
}
