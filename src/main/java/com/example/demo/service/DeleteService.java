package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DeleteMapper;

@Service
public class DeleteService {

	@Autowired
	private DeleteMapper deleteMapper;
	
	public int delete(List<Integer>idList) {
		int delNum = 0;
		for(int id : idList) {
			delNum = deleteMapper.delete(id);
		}return delNum;
	}
}
