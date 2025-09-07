package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DeleteMapper;

@Service
public class DeleteService {

	@Autowired
	private DeleteMapper deleteMapper;
	
	public void delete(List<Integer>idList) {
		for(int id : idList) {
			deleteMapper.delete(id);
		}
	}
}
