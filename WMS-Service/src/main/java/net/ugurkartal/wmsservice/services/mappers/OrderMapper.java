package net.ugurkartal.wmsservice.services.mappers;

import net.ugurkartal.wmsservice.models.Order;
import net.ugurkartal.wmsservice.services.dtos.OrderDto;
import net.ugurkartal.wmsservice.services.requests.OrderCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderDto orderToOrderDtoMapper(Order order) {
        return new OrderDto(order.getId(), order.getCustomerName(), order.getOrderDate(), order.getOrderStatus());
    }

    public Order createRequestToOrderMapper(OrderCreateRequest orderCreateRequest) {
        return Order.builder()
                .customerName(orderCreateRequest.getCustomerName())
                .build();
    }

    public Order updateRequestToOrderMapper(OrderUpdateRequest orderUpdateRequest) {
        return Order.builder()
                .orderStatus(orderUpdateRequest.getOrderStatus())
                .build();
    }
}