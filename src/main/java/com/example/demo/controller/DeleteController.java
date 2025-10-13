package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
		int id = (int)this.session.getAttribute("keyId");
		return "deleteForm";
	}
	
//	localhost:8080/delete/checkにアクセス→削除確認画面を表示
	@PostMapping("/check")
	public String deleteCheck(
			Model model,
			@RequestParam("id")List<String>idList,
			@RequestParam("url")String url){
		try{
			List<Integer>numIdList = new ArrayList<Integer>();
			for(String id : idList) {
				numIdList.add(Integer.parseInt(id));
			}
//			空欄だった場合のエラー処理
			if(numIdList.isEmpty()) {
				model.addAttribute("error","1");
				return "deleteForm";
				
//			成功した場合の処理
			}else{
				model.addAttribute("check",numIdList);
				model.addAttribute("back",url);
				return "deleteCheck";
			}

//			数字以外が入力された場合のエラー処理
		}	catch(NumberFormatException e) {
			model.addAttribute("error","2");
			model.addAttribute("id",idList.get(0));
			return "deleteForm";
			}
		
	}
	
//	localhost:8080/delete/executionにアクセス→削除処理→削除完了画面
	@PostMapping("/execution")
	public String deleteExecution(
			Model model,
			@RequestParam("id")List<Integer>idList,
			@RequestParam("url")String url) {
		int id = (int)this.session.getAttribute("keyId");
		
//		自分のデータ削除しようとした場合のエラー処理
		if(idList.contains(id)) {
			model.addAttribute("error","3");
			model.addAttribute("check",idList);
			model.addAttribute("back",url);
			return "deleteCheck";
		}
		
		int delNum = deleteService.delete(idList);
		
//		対象データがなかった場合のエラー処理		
		if(delNum == 0) {
			model.addAttribute("error","4");
			model.addAttribute("check",idList);
			model.addAttribute("back",url);
			return "deleteCheck";
		}
		
		else {
			model.addAttribute("comp","社員情報の削除が完了しました");
			return "deleteCompletion";
		}
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
					model.addAttribute("id",idList);
					return "dummySearch";

				}
			}
	

}
