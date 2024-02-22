package net.ugurkartal.wmsservice.controllers;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.services.abstracts.PurchaseService;
import net.ugurkartal.wmsservice.services.dtos.PurchaseDto;
import net.ugurkartal.wmsservice.services.requests.PurchaseCreateRequest;
import net.ugurkartal.wmsservice.services.requests.PurchaseUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDto> getAll() {
        return purchaseService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDto getById(@PathVariable String id) {
        return purchaseService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDto add(@RequestBody PurchaseCreateRequest purchaseCreateRequest) {
        return purchaseService.add(purchaseCreateRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDto update(@RequestParam String id, @RequestBody PurchaseUpdateRequest purchaseUpdateRequest) {
        return purchaseService.update(id, purchaseUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        purchaseService.deleteById(id);
    }
}