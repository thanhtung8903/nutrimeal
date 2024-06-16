package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import com.nutrimeal.nutrimeal.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    //    get 3 day menu
    // get 3 day menu with isActive=true
    List<DailyMenu> findAllByIsActiveTrueAndDailyMenuDateBetweenOrderByDailyMenuDateAsc(Date startDate, Date endDate);

    // paging with isActive=true
    Page<DailyMenu> findAllByIsActiveTrueOrderByDailyMenuDateDesc(Pageable pageable);

    // check existence with isActive=true
    boolean existsDailyMenuByDailyMenuDateAndDailyMenuTypeAndIsActiveTrue(Date date, String type);

    @Query("SELECT dm FROM DailyMenu dm WHERE "
            + "(:date IS NULL OR dm.dailyMenuDate = :date) AND "
            + "(:type IS NULL OR dm.dailyMenuType = :type) AND "
            + "(:name IS NULL OR dm.dishBreakfast.dishName LIKE %:name% OR "
            + "dm.dishLunch.dishName LIKE %:name% OR "
            + "dm.dishDinner.dishName LIKE %:name%)")
    Page<DailyMenu> searchDailyMenu(@Param("date") Date date,
                                    @Param("type") String type,
                                    @Param("name") String name,
                                    Pageable pageable);

    DailyMenu findByDailyMenuDateAndDailyMenuTypeAndIsActiveTrue(Date date, String type);

}
