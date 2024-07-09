package com.nutrimeal.nutrimeal.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PointHistoryResponse {
    private int pointHistoryId;
    private LocalDate pointHistoryDate;
    private String pointHistoryDescription;
    private String pointHistoryStatus;
    private int pointHistoryPoint;
    private String userId;

}
