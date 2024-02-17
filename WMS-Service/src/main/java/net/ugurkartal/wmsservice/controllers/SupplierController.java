package net.ugurkartal.wmsservice.controllers;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.services.abstracts.SupplierService;
import net.ugurkartal.wmsservice.services.dtos.SupplierDto;
import net.ugurkartal.wmsservice.services.requests.SupplierCreateRequest;
import net.ugurkartal.wmsservice.services.requests.SupplierUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SupplierDto> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierDto getById(@PathVariable String id) {
        return supplierService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier add(@RequestBody SupplierCreateRequest supplierCreateRequest) {
        return supplierService.add(supplierCreateRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Supplier update(@RequestParam String id, @RequestBody SupplierUpdateRequest supplierUpdateRequest) {
        return supplierService.update(id, supplierUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        supplierService.deleteById(id);
    }
}