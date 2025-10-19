package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Staff;
import com.example.demo.service.SearchService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchservice;
	@Autowired
	private HttpSession session;

	private boolean setLoginInfo(Model m) {
		String loginName = (String) session.getAttribute("keyName");
		LocalDateTime loginDateTime = (LocalDateTime) session.getAttribute("keyDateTime");
		String loginIdStr = (String) session.getAttribute("keyId");
		Integer loginId = loginIdStr != null ? Integer.parseInt(loginIdStr) : null;

		if (loginName == null || loginDateTime == null || loginId == null) {
			return false;
		}
		m.addAttribute("loginName", loginName);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("loginId", loginId);
		return true;
	}

	// 初期表示（全件）
	@GetMapping("/searchForm")
	public String getSearchForm(Model m) {
		if (!setLoginInfo(m)) {
			return "redirect:/login";
		}
		m.addAttribute("staffList", null);
		m.addAttribute("staff", new Staff());
		return "search";
	}

	//全件表示
	@GetMapping("/list")
	public String getSearchList(Model m) {
		if (!setLoginInfo(m)) {
			return "redirect:/login";
		}
		List<Staff> searchList = searchservice.searchAll();
		// Modelに登録
		m.addAttribute("staffList", searchList);
		m.addAttribute("staff", new Staff());
		return "search";
	}

	//ユーザー検索処理
	@PostMapping(value = "/list", params = "search")
	public String searchUser(Staff staff, BindingResult result, Model m) {
		if (!setLoginInfo(m)) {
			return "redirect:/login";
		}

		//入力チェック：IDは数字のみ
		//もしID欄に数字以外が入れられた時、エラーメッセージ
		if (staff.getId() != null && !staff.getId().isEmpty()) {
			if (!staff.getId().matches("\\d+")) {
				result.rejectValue("id", "id.invalid", "社員IDは数字のみ入力可能です");
			}
		}

		//年齢範囲チェック    
		Integer ageFromInt = null;
		Integer ageToInt = null;

		//年齢相関チェック
		try {
			if (staff.getAgeFrom() != null && !staff.getAgeFrom().isEmpty()) {
				ageFromInt = Integer.parseInt(staff.getAgeFrom());
			}
			if (staff.getAgeTo() != null && !staff.getAgeTo().isEmpty()) {
				ageToInt = Integer.parseInt(staff.getAgeTo());
			}

			if (ageFromInt != null && ageToInt != null && ageFromInt > ageToInt) {
				result.rejectValue("ageFrom", "age.range", "年齢の範囲指定が正しくありません（From ≦ To にしてください）");
			}
		} catch (NumberFormatException e) {
			result.rejectValue("ageFrom", "age.format", "年齢は数値で入力してください");
		}
		//もしバリデーションエラーがある時、全体表示＋エラーメッセージ
		if (result.hasErrors()) {
			m.addAttribute("staffList", searchservice.searchAll());
			m.addAttribute("staff", staff);
			return "search";
		}

		// 検索条件として Integer を渡す
		List<Staff> searchList = searchservice.searchUser(
				staff.getId(),
				staff.getName(),
				ageFromInt,
				ageToInt,
				staff.getStartDateFrom(),
				staff.getStartDateTo(),
				staff.getEndDateFrom(),
				staff.getEndDateTo());
		// 検索入力
		if (searchList.isEmpty()) {
			// 検索結果がない場合のメッセージを設定
			m.addAttribute("NoList", "検索結果がありません");
		}

		//件数表示
		m.addAttribute("resultCount", searchList.size());

		// 結果表示
		m.addAttribute("staffList", searchList);
		m.addAttribute("staff", staff);

		System.out.println("検索条件：" + staff);
		System.out.println("検索結果件数：" + searchList.size());
		if (!searchList.isEmpty()) {
			System.out.println("検索結果１件目のID＝" + searchList.get(0).getId());
		}
		return "search";

	}

	/* クリア処理 */
	@PostMapping(value = "/list", params = "clear")
	public String clearSearchList() {
		if (session.getAttribute("keyId") == null) {
			return "redirect:/login";
		}
		// 一覧表示画面に戻る
		return "redirect:/search/searchForm";
	}

	// 削除画面に遷移
	@PostMapping(value = "/list", params = "delete")
	public String goToDelete(
			@RequestParam(value = "selectedIds", required = false) List<String> selectedIdsStr, Model m) {

	    if (selectedIdsStr == null || selectedIdsStr.isEmpty()) {
	        m.addAttribute("NoList", "削除対象を1件以上選択してください");
	        m.addAttribute("staffList", searchservice.searchAll());
	        m.addAttribute("staff", new Staff());
	        return "search"; // 元の画面に戻る
	    }
	    // String → Integer に変換
	    List<Integer> selectedIds = new ArrayList<>();
	    for (String s : selectedIdsStr) {
	        try {
	            selectedIds.add(Integer.valueOf(s));
	        } catch (NumberFormatException e) {
	            // 数字以外は無視
	        }
	    }

	    // Integer 型としてセッションに格納
	    session.setAttribute("idList", selectedIds);

	    // デバッグ用：型確認
	    System.out.println("idList セッションに格納: " + selectedIds + " 型: " + selectedIds.getClass().getName());
	    return "redirect:/delete/form"; // 削除フォームへ
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true) {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (text == null || text.trim().isEmpty()) {
					setValue(null);
				} else {
					try {
						setValue(Integer.valueOf(text.trim()));
					} catch (NumberFormatException e) {
						// 型変換に失敗したら例外を投げる
						throw new IllegalArgumentException("整数を入力してください");
					}
				}
			}
		});
	}
}