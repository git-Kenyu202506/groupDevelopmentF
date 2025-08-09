package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Staff {
//	フィールド
	private int id;
	private String name;
	private String password;
	private int age;
	private LocalDateTime startDate;
	private LocalDate endDate;
	
//	Getter、Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDateTime getStart_date() {
		return startDate;
	}
	public void setStart_date(LocalDateTime start_date) {
		this.startDate = start_date;
	}
	public LocalDate getEnd_date() {
		return endDate;
	}
	public void setEnd_date(LocalDate end_date) {
		this.endDate = end_date;
	}
	
//	コンストラクタ
	public Staff() {}
	
	public Staff
	(int id, String name, String password, int age, LocalDateTime start_date, LocalDate end_date) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.startDate = start_date;
		this.endDate = end_date;		
	}

}
