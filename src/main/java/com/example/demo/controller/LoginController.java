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
	@PostMapping("/topmenu")
		public String setLogin(
				Model model,
				@RequestParam("id")String id,
				@RequestParam("password")String password) {
//		空欄だった場合のエラー処理
		if(id.isEmpty()) {
			model.addAttribute("error","1");
			model.addAttribute("password",password);
			return "loginForm";
			
		}else if(password.isEmpty()) {
			model.addAttribute("id",id);
			model.addAttribute("error","1");
			return "loginForm";
		}	
		
		try{
			int numid = Integer.parseInt(id);
			Staff staff = loginService.login(numid,password);
	
//		対象データがなかった場合のエラー処理	
		if(staff == null) {
			model.addAttribute("error","3");
			model.addAttribute("id",id);
			model.addAttribute("password",password);
			return "loginForm";
		}
//		ログイン成功	
		else {
			this.session.setAttribute("keyName",staff.getName());
			this.session.setAttribute("keyDateTime",LocalDateTime.now());
			this.session.setAttribute("keyId", staff.getId());
			return "main";
		}
		
		}
//		idに数字以外が入力された場合のエラー処理
		catch(NumberFormatException e) {
			model.addAttribute("error","2");
			model.addAttribute("id",id);
			model.addAttribute("password",password);
			return "loginForm";
			}
	}
}
