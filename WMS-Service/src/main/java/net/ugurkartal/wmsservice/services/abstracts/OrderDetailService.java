package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.services.dtos.OrderDetailDto;
import net.ugurkartal.wmsservice.services.requests.OrderCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderDetailCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderDetailUpdateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderUpdateRequest;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAll();
    OrderDetailDto getById(String  id);
    OrderDetailDto add(OrderDetailCreateRequest orderDetailCreateRequest);
    OrderDetailDto update(String id, OrderDetailUpdateRequest orderDetailUpdateRequest);
    boolean delete(String id);
}