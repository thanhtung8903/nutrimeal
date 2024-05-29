package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.repository.DailyMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DailyMenuTest {

    private final DailyMenuRepository dailyMenuRepository;

    @GetMapping("/daily")
    public List<DailyMenu> getDailyMenu() {
        return dailyMenuRepository.findAll();
    }

    @GetMapping("/daily/menu")
    public List<DailyMenu> getDailyMenuByDate() {
        // Lấy ngày hiện tại
        LocalDate startDate = LocalDate.now();
        // Lấy ngày hiện tại cộng thêm 2 ngày (tổng cộng là 3 ngày)
        LocalDate endDate = startDate.plusDays(3);
        // Chuyển đổi LocalDate sang java.sql.Date
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);
        // Truy vấn cơ sở dữ liệu với khoảng thời gian
        return dailyMenuRepository.findByDailyMenuDateBetween(sqlStartDate, sqlEndDate);
    }
}
