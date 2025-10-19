package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.DeleteService;

import jakarta.servlet.http.HttpSession;

//@Controller
@RequestMapping("/delete")
public class DummySearchController {
	@Autowired
	private DeleteService deleteService;
	
	@Autowired
	private HttpSession session;
	
//	localhost:8080/delete/dummy/searchformにアクセス→削除入力画面を表示
	@GetMapping("dummy/searchform")
	public String deleteForm(Model model) {
		String name  = (String)this.session.getAttribute("keyName");
		LocalDateTime dateTime = (LocalDateTime)this.session.getAttribute("keyDateTime");
		int id = (int)this.session.getAttribute("keyId");
		return "dummySearch";
	}
	
}
