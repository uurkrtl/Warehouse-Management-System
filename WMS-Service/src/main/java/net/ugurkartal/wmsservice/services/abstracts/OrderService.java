package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.services.dtos.OrderDto;
import net.ugurkartal.wmsservice.services.requests.OrderCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderUpdateRequest;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();
    OrderDto getById(String id);
    OrderDto add(OrderCreateRequest orderCreateRequest);
    OrderDto update(String id, OrderUpdateRequest orderUpdateRequest);
    boolean delete(String id);
}