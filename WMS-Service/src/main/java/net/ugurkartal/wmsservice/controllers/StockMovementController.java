package net.ugurkartal.wmsservice.controllers;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.services.abstracts.StockMovementService;
import net.ugurkartal.wmsservice.services.dtos.StockMovementDto;
import net.ugurkartal.wmsservice.services.requests.StockMovementCreateRequest;
import net.ugurkartal.wmsservice.services.requests.StockMovementUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {
    private final StockMovementService stockMovementService;

    @GetMapping
    public List<StockMovementDto> getAll() {
        return stockMovementService.getAll();
    }

    @GetMapping("/{id}")
    public StockMovementDto getById(@PathVariable String id) {
        return stockMovementService.getById(id);
    }

    @PostMapping("/add")
    public StockMovementDto add(@RequestBody StockMovementCreateRequest stockMovementCreateRequest) {
        return stockMovementService.add(stockMovementCreateRequest);
    }

    @PutMapping("/update")
    public StockMovementDto update(@RequestParam String id, @RequestBody StockMovementUpdateRequest stockMovementUpdateRequest) {
        return stockMovementService.update(id, stockMovementUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        stockMovementService.deleteById(id);
    }
}