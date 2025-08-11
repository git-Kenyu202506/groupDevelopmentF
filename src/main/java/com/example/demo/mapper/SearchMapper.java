package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Staff;

	@Mapper
	public interface SearchMapper {
		
		Staff searchStaffByID(int id);
		
		@Select("SELECT * FROM Staff")
		List<Staff> selectAll();
	}
