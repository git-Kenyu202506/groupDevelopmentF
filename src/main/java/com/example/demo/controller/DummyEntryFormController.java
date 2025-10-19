package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
@Controller
public class DummyEntryFormController {

	// 社員情報登録画面表示
	@GetMapping("/entryForm")
	public String showEntryForm(Model m, HttpSession session) {
		// ログイン情報をModelに渡す（必要なら）
		m.addAttribute("keyName", session.getAttribute("keyName"));
		m.addAttribute("keyDateTime", session.getAttribute("keyDateTime"));

		return "entryForm"; // templates/entryForm.html を返す
	}
}
