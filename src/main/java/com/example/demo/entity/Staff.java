package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Staff {
	//	フィールド
	private String id;
	private String name;
	private String password;
	private Integer age;
	private Integer ageFrom;
	private Integer ageTo;
	private LocalDateTime startDate;
	private LocalDate endDate;
	private LocalDate startDateFrom;
	private LocalDate startDateTo;
	private LocalDate endDateFrom;
	private LocalDate endDateTo;

	//	Getter、Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}

	public Integer getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDate getStartDateFrom() {
		return startDateFrom;
	}

	public void setStartDateFrom(LocalDate startDateFrom) {
		this.startDateFrom = startDateFrom;
	}

	public LocalDate getStartDateTo() {
		return startDateTo;
	}

	public void setStartDateTo(LocalDate startDateTo) {
		this.startDateTo = startDateTo;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getEndDateFrom() {
		return endDateFrom;
	}

	public void setEndDateFrom(LocalDate endDateFrom) {
		this.endDateFrom = endDateFrom;
	}

	public LocalDate getEndDateTo() {
		return endDateTo;
	}

	public void setEndDateTo(LocalDate endDateTo) {
		this.endDateTo = endDateTo;
	}

	//	コンストラクタ
	public Staff() {
	}

	public Staff(String id, String name, String password, Integer age, LocalDateTime startDate, LocalDate endDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
