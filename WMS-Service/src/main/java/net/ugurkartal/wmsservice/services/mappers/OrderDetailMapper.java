package net.ugurkartal.wmsservice.services.mappers;

import net.ugurkartal.wmsservice.models.OrderDetail;
import net.ugurkartal.wmsservice.services.dtos.OrderDetailDto;
import net.ugurkartal.wmsservice.services.requests.OrderDetailCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderDetailUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailMapper {
    public OrderDetailDto orderDetailToOrderDetailDtoMapper(OrderDetail orderDetail) {
        return new OrderDetailDto(orderDetail.getId(), orderDetail.getOrder(), orderDetail.getProduct(), orderDetail.getQuantity(), orderDetail.getPrice(), orderDetail.getTotalPrice(), orderDetail.isActive());
    }

    public OrderDetail createRequestToOrderDetailMapper(OrderDetailCreateRequest orderDetailCreateRequest) {
        return OrderDetail.builder()
                .quantity(orderDetailCreateRequest.getQuantity())
                .build();
    }

    public OrderDetail updateRequestToOrderDetailMapper(OrderDetailUpdateRequest orderDetailUpdateRequest) {
        return OrderDetail.builder()
                .quantity(orderDetailUpdateRequest.getQuantity())
                .isActive(orderDetailUpdateRequest.isActive())
                .build();
    }
}