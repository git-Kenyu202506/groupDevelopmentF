package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.DeleteService;

import jakarta.servlet.http.HttpSession;

@Controller

@RequestMapping("/delete")
public class DeleteController {
	
	@Autowired
	private DeleteService deleteService;
	
	@Autowired
	private HttpSession session;
	
//	localhost:8080/delete/formにアクセス→削除入力画面を表示
	@GetMapping("/form")
	public String deleteForm(Model model) {
		String name  = (String)this.session.getAttribute("keyName");
		LocalDateTime dateTime = (LocalDateTime)this.session.getAttribute("keyDateTime");
		return "deleteForm";
	}
	
//	localhost:8080/delete/checkにアクセス→削除確認画面を表示
	@PostMapping("/check")
	public String deleteCheck(
			Model model,
			@RequestParam("id")List<Integer>idList,
			@RequestParam("url")String url){
		model.addAttribute("check",idList);
		model.addAttribute("back",url);
		return "deleteCheck";
	}
	
//	localhost:8080/delete/executionにアクセス→削除処理→削除完了画面
	@PostMapping("/execution")
	public String deleteExecution(
			Model model,
			@RequestParam("id")List<Integer>idList) {
		deleteService.delete(idList);
		model.addAttribute("comp","削除が完了しました");
		return "deleteCompletion";
	}
	
	
//	localhost:8080/delete/backにアクセス→urlに応じたページに遷移[戻る処理]
	@GetMapping("/back")
	public String deleteBack(
			Model model,
			@RequestParam("url")String url,
			@RequestParam("id")List<Integer>idList){
		
				if(url.equals ("deleteForm")) {
					model.addAttribute("id",idList.get(0));
					return "deleteForm";
				}else {
					return "searchForm";
				}
			}
	

}
