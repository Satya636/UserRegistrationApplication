package com.sit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashBoardController {

	
	@GetMapping("displayDashBoard")
	public String displayUserDashBoard(Model model) {
		return "user_dashBoard";
	}
	
}
