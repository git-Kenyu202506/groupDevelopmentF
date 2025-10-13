package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Staff;

@Mapper

public interface LoginMapper {
	@Select("SELECT * FROM staff WHERE id = #{id} AND password = #{password}")
	Staff login(int id,String password);
}
