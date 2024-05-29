package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.repository.DailyMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyMenuService {

    private final DailyMenuRepository dailyMenuRepository;

    public List<DailyMenu> findByDailyMenuDateBetween(Date startDate, Date endDate) {
        return dailyMenuRepository.findByDailyMenuDateBetween(startDate, endDate);
    }

    public List<DailyMenu> getAllDailyMenu() {
        return dailyMenuRepository.findAll();
    }

    public Page<DailyMenu> getAllDailyMenu(Pageable pageable) {
        return dailyMenuRepository.findAllByOrderByDailyMenuDateDesc(pageable);
    }

    public void addDailyMenu(DailyMenu dailyMenu) {
        dailyMenuRepository.save(dailyMenu);
    }

    public boolean existsDailyMenuByDailyMenuDateAndDailyMenuType(Date date, String type) {
        return dailyMenuRepository.existsDailyMenuByDailyMenuDateAndDailyMenuType(date, type);
    }

    public DailyMenu getDailyMenuById(Integer id) {
        return dailyMenuRepository.findById(id).orElse(null);
    }

    public void deleteDailyMenu(Integer id) {
        dailyMenuRepository.deleteById(id);
    }

    public void updateDailyMenu(Integer id, DailyMenu dailyMenu) {
        DailyMenu oldDailyMenu = dailyMenuRepository.findById(id).orElse(null);
        if (oldDailyMenu != null) {
            oldDailyMenu.setDailyMenuDate(dailyMenu.getDailyMenuDate());
            oldDailyMenu.setDailyMenuType(dailyMenu.getDailyMenuType());
            oldDailyMenu.setDishBreakfast(dailyMenu.getDishBreakfast());
            oldDailyMenu.setDishLunch(dailyMenu.getDishLunch());
            oldDailyMenu.setDishDinner(dailyMenu.getDishDinner());
            dailyMenuRepository.save(oldDailyMenu);
        }
    }

}
