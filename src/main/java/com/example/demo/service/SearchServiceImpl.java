package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Staff;
import com.example.demo.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchMapper searchMapper;

	//ユーザー情報取得
	@Override
	public List<Staff> searchAll() {
		return searchMapper.searchAll();
	}

	@Override
	public List<Staff> searchUser(
			String id,
			String name,
			Integer ageFrom,
			Integer ageTo,
			LocalDate startDateFrom,
			LocalDate startDateTo,
			LocalDate endDateFrom,
			LocalDate endDateTo) {
		return searchMapper.searchUserByConditions(
				id, name, ageFrom, ageTo, startDateFrom, startDateTo, endDateFrom, endDateTo);
	}

}
