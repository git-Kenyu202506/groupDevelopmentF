package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Staff;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpSession session;
	
//	ログイン画面表示（localhost:8080/login）
	@GetMapping("/login")
		public String getLogin(Model model) {
		return "loginForm";
	}
	
//	ログイン処理
	@PostMapping("/login")
		public String setLogin(
				Model model,
				@RequestParam("id")String id,
				@RequestParam("password")String password) {
		try{
			int numid = Integer.parseInt(id);
			Staff staff = loginService.login(numid,password);
		
//		ログイン成功
		if(staff != null) {
			this.session.setAttribute("keyName",staff.getName());
			this.session.setAttribute("keyDateTime",LocalDateTime.now());
			this.session.setAttribute("keyId", staff.getId());
			return "main";
		}else {
			model.addAttribute("error","※入力が間違っています");
			return "loginForm";
			}
		}
		
		catch(NumberFormatException e) {
			model.addAttribute("error","※入力が間違っています");
			return "loginForm";
			}
	}
}
