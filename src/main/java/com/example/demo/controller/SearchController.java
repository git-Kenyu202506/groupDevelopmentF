package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Staff;
import com.example.demo.service.SearchService;

@Controller
@RequestMapping("/searchAll")
public class SearchController {
	@Autowired
	private SearchService searchservice;

	@GetMapping
	public String searchAll(Model m) {
		List<Staff> staff = searchservice.searchAll();
		m.addAttribute("staff", staff);
		return "searchAll";
	}
}