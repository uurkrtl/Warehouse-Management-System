package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Order;
import net.ugurkartal.wmsservice.models.enums.OrderStatus;
import net.ugurkartal.wmsservice.repositories.OrderRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.OrderService;
import net.ugurkartal.wmsservice.services.dtos.OrderDto;
import net.ugurkartal.wmsservice.services.mappers.OrderMapper;
import net.ugurkartal.wmsservice.services.requests.OrderCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final GenerateIDService generateIDService;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = this.orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream().map(order -> orderMapper.orderToOrderDtoMapper(order)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public OrderDto getById(String id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        OrderDto orderDto = this.orderMapper.orderToOrderDtoMapper(order);
        return orderDto;
    }

    @Override
    public OrderDto add(OrderCreateRequest orderCreateRequest) {
        Order order = this.orderMapper.createRequestToOrderMapper(orderCreateRequest);

        String newId = this.generateIDService.generateOrderId();
        order.setId(newId);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);

        order = this.orderRepository.save(order);
        return this.orderMapper.orderToOrderDtoMapper(order);
    }

    @Override
    public OrderDto update(String id, OrderUpdateRequest orderUpdateRequest) {
        Order foundOrder = this.orderRepository.findById(id).orElse(null);

        foundOrder.setOrderStatus(orderUpdateRequest.getOrderStatus());
        foundOrder = this.orderRepository.save(foundOrder);
        return this.orderMapper.orderToOrderDtoMapper(foundOrder);
    }

    @Override
    public boolean delete(String id) {
        this.orderRepository.deleteById(id);
        return true;
    }
}