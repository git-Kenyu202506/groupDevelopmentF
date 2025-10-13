package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Staff;

@Mapper
public interface SearchMapper {

	/*ユーザー全表示*/
	public List<Staff> searchAll();

	/*ユーザー検索表示*/
	public List<Staff> searchUser(Staff staff);
	
    long countSearch();
	}

