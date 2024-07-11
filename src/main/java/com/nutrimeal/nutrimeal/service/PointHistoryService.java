package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.dto.response.PointHistoryResponse;
import com.nutrimeal.nutrimeal.model.PointHistory;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;

    public void save(PointHistory pointHistory) {
        pointHistoryRepository.save(pointHistory);
    }

    public List<PointHistory> getAllPointHistory() {
        return pointHistoryRepository.findAll();
    }

    public List<PointHistoryResponse> getPointHistoryByUser(User user) {
        var listPointHistory = pointHistoryRepository.findAllByUserOrderByPointHistoryIdDesc(user);

        return listPointHistory.stream()
                .map(pointHistory -> PointHistoryResponse.builder()
                        .pointHistoryId(pointHistory.getPointHistoryId())
                        .pointHistoryDate(pointHistory.getPointHistoryDate())
                        .pointHistoryDescription(pointHistory.getPointHistoryDescription())
                        .pointHistoryStatus(pointHistory.getPointHistoryStatus())
                        .pointHistoryPoint(pointHistory.getPointHistoryPoint())
                        .userId(pointHistory.getUser().getUserId())
                        .build())
                .toList();
    }
}
