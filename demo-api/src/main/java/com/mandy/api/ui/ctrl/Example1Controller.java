package com.mandy.api.ui.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping(value = "eg1")
public class Example1Controller {
	
	static String TOPIC = "eg1"; //   for script.dev.jsp / var t = '${topic}';    =>       dev.js / var $q 
	
	@GetMapping(value = "")
	public String index() {
		return "redirect:/eg1/one";
	}
	
	@GetMapping(value = "one")
	public String one(final Model model) {
		model.addAttribute("topic", TOPIC);
		return "/eg1/one";
	}
	
	@GetMapping(value = "two")
	public String two(final Model model) {
		model.addAttribute("topic", TOPIC);
		return "/eg1/two";
	}
	
	@GetMapping(value = "three")
	public String three(final Model model) {
		model.addAttribute("topic", TOPIC);
		return "/eg1/three";
	}
}
