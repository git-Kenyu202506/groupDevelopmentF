package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Staff;

@Service
public interface SearchService {
	//ユーザー取得
	public List<Staff> searchAll();

	//検索条件ユーザー取得
	public List<Staff> searchUser(Staff staff);

}
