package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.StaffService;

@Controller
public class DummyUpdateController {

	@Autowired
	private StaffService staffService;

	@GetMapping("/updateForm")
	public String showUpdateForm(@RequestParam("id") String id, Model model) {
		model.addAttribute("staffId", id);
		return "updateForm";
	}

}
