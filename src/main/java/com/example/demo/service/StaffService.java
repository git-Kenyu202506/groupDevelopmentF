package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Staff;

@Service
public class StaffService {
    public Staff findById(Integer id) {
        Staff staff = new Staff();
        staff.setId(String.valueOf(id)); // Integer → String
        staff.setName("テスト社員" + id);
        staff.setAge(30); // 仮の年齢
        staff.setStartDate(java.time.LocalDate.of(2020, 1, 1).atStartOfDay()); // LocalDate → LocalDateTime
        staff.setEndDate(java.time.LocalDate.of(2025, 12, 31));
        return staff;
    }
}
