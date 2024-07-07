package com.nutrimeal.nutrimeal.dto.response;

import com.nutrimeal.nutrimeal.model.DeliveryDetail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class DeliveryDetailResponse {

//    private DeliveryResponse delivery;

    private List<DeliveryDetail> deliveryDetails;



}
