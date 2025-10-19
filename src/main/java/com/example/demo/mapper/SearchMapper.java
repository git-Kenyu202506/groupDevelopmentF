package com.example.demo.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Staff;

@Mapper
public interface SearchMapper {

	/*ユーザー全表示*/
	public List<Staff> searchAll();

	/*ユーザー検索表示*/
	public List<Staff> searchUser(Staff staff);

	/* ユーザー検索（条件指定版） */
	List<Staff> searchUserByConditions(
			@Param("id") String id,
			@Param("name") String name,
			@Param("ageFrom") Integer ageFrom,
			@Param("ageTo") Integer ageTo,
			@Param("startDateFrom") LocalDate startDateFrom,
			@Param("startDateTo") LocalDate startDateTo,
			@Param("endDateFrom") LocalDate endDateFrom,
			@Param("endDateTo") LocalDate endDateTo);
}
