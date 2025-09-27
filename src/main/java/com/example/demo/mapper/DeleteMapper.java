package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeleteMapper {
	
	@Delete("DELETE FROM staff WHERE id = #{id}")
	public int delete(int id);
}
