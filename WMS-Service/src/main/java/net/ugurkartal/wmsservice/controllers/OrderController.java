package net.ugurkartal.wmsservice.controllers;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.services.abstracts.OrderService;
import net.ugurkartal.wmsservice.services.dtos.OrderDto;
import net.ugurkartal.wmsservice.services.requests.OrderCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable String id) {
        return orderService.getById(id);
    }

    @PostMapping("/add")
    public OrderDto add(@RequestBody OrderCreateRequest orderCreateRequest) {
        return orderService.add(orderCreateRequest);
    }

    @PutMapping("/update")
    public OrderDto update(@RequestParam String id, @RequestBody OrderUpdateRequest orderUpdateRequest) {
        return orderService.update(id, orderUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        orderService.delete(id);
    }
}