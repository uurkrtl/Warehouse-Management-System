package net.ugurkartal.wmsservice.controllers;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.services.abstracts.OrderDetailService;
import net.ugurkartal.wmsservice.services.dtos.OrderDetailDto;
import net.ugurkartal.wmsservice.services.requests.OrderDetailCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderDetailUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDetailDto getById(String id) {
        return orderDetailService.getById(id);
    }

    @PostMapping("/add")
    public OrderDetailDto add(@RequestBody OrderDetailCreateRequest orderDetailCreateRequest) {
        return orderDetailService.add(orderDetailCreateRequest);
    }

    @PutMapping("/update")
    public OrderDetailDto update(@RequestParam String id, @RequestBody OrderDetailUpdateRequest orderDetailUpdateRequest) {
        return orderDetailService.update(id, orderDetailUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        orderDetailService.delete(id);
    }
}