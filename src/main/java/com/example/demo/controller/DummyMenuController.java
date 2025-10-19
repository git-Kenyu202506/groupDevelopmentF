package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DummyMenuController {

	// メインメニュー表示
    @GetMapping("/main")
    public String showMain(Model model, HttpSession session) {
        // セッションにログイン情報があると仮定
        Object keyName = session.getAttribute("keyName");
        Object keyDateTime = session.getAttribute("keyDateTime");

        model.addAttribute("keyName", keyName != null ? keyName : "ゲスト");
        model.addAttribute("keyDateTime", keyDateTime != null ? keyDateTime : "未ログイン");

        return "main"; // main.html を返す
    }
}
