package com.nutrimeal.nutrimeal.controller;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.repository.DailyMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
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
        Calendar calendar = Calendar.getInstance();
        Date startDate = (Date) calendar.getTime();

        // Tăng ngày lên 3 ngày
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date endDate = (Date) calendar.getTime();

        return dailyMenuRepository.findByDailyMenuDateBetween(startDate, endDate);
    }
}
