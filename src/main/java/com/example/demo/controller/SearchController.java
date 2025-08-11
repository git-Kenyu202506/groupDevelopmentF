package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Staff;
import com.example.demo.service.SearchService;

@Controller
@RequestMapping("/Staff")
public class SearchController {
		@Autowired		
		private SearchService searchservice;
		@Autowired		
		private ModelMapper modelMapper;

		//一覧画面表示
		@GetMapping("/StaffList")
		public String getStaffList(@ModelAttribute StaffListForm form, Model m) {
			
			
			
			//ユーザー検索
			List<Staff> staff = searchservice.getstaffList(staff);
			
			//Modelに登録
			m.addAttribute("staffList", staffList);
			return "/staff/list";
		}
	}
