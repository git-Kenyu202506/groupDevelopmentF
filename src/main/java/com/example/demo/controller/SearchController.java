package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Staff;
import com.example.demo.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchservice;

	//全件表示
	@GetMapping("/list")
	public String getSearchList(Model m) {
		// 初期表示（全件）
		List<Staff> searchList = searchservice.searchAll();
		// Modelに登録
		m.addAttribute("staffList", searchList);
		m.addAttribute("staff", new Staff());
		return "search";
	}

	//ユーザー検索処理
	@PostMapping(value = "/list", params = "search")
	public String searchUser(Staff staff, BindingResult result, Model m) {

		//入力チェック：IDは数字のみ
		//もしID欄に数字以外が入れられた時、エラーメッセージ
		if (staff.getId() != null && !staff.getId().isEmpty()) {
			if (!staff.getId().matches("\\d+")) {
				result.rejectValue("id", "id.invalid", "社員IDは数字のみ入力可能です");
			}
		}

		//年齢範囲チェック
		Integer ageFrom = staff.getAgeFrom();
		Integer ageTo = staff.getAgeTo();

		//年齢相関チェック
		if (ageFrom != null && ageTo != null) {
			if (ageFrom > ageTo) {
				result.rejectValue("ageFrom", "age.range", "年齢の範囲指定が正しくありません（From ≦ To にしてください）");
			}
		}

		if (result.hasErrors()) {
			//もしバリデーションエラーがある時、全体表示＋エラーメッセージ
			m.addAttribute("staffList", searchservice.searchAll());
			m.addAttribute("staff", staff);
			return "search";
		}
		// 検索入力
		List<Staff> searchList = searchservice.searchUser(staff);
		if (searchList.isEmpty()) {
			// 検索結果がない場合のメッセージを設定
			m.addAttribute("NoList", "検索結果がありません");
		}
		// 結果表示
		m.addAttribute("staffList", searchList);
		m.addAttribute("staff", staff);

		if (!searchList.isEmpty()) {
			System.out.println("検索結果１件目のID＝" + searchList.get(0).getId());
		}
		return "search";
		
	}

	/* クリア処理 */
	@PostMapping(value = "/list", params = "clear")
	public String clearSearchList() {

		// 一覧表示画面に戻る
		return "redirect:/search/list";
	}
}