package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Staff;
import com.example.demo.mapper.LoginMapper;



@Service
public class LoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	public Staff login(int id,String password) {
		return loginMapper.login(id,password);
	}
}
