package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.LoginService;

@Controller

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
		public String getLogin(Model model) {
		return "loginForm";
	}
}
