package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    //    get 3 day menu
    public List<DailyMenu> findByDailyMenuDateBetween(Date startDate, Date endDate);

    //    paging
    public Page<DailyMenu> findAllByOrderByDailyMenuDateDesc(Pageable pageable);

    boolean existsDailyMenuByDailyMenuDateAndDailyMenuType(Date date, String type);

}
