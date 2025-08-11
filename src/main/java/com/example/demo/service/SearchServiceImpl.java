package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Staff;
import com.example.demo.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchMapper mapper;

	//ユーザー情報取得
	@Override
	public List<Staff> getAllStaff() {
		return mapper.selectAllStaff();
	}

	//検索条件で一致するユーザーを取得
	@Override
	public List<Staff> getSearchStaff(Staff staff) {
		return mapper.searchStaff(staff);
	}
}
