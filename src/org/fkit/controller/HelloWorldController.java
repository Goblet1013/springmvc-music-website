package org.fkit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	@RequestMapping("/helloWorld")
	public String helloWorld(Model model) {
		model.addAttribute("message","Hello World");
		return "welcome";
	}
}
