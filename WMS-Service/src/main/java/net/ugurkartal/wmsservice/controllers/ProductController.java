package net.ugurkartal.wmsservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.services.abstracts.ProductService;
import net.ugurkartal.wmsservice.services.dtos.ProductDto;
import net.ugurkartal.wmsservice.services.requests.ProductCreateRequest;
import net.ugurkartal.wmsservice.services.requests.ProductUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getById(@PathVariable String id) {
        return productService.getById(id);
    }

    @PostMapping("/add")
    //@ResponseStatus(HttpStatus.CREATED)
    public ProductDto add(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        return productService.add(productCreateRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto update(@RequestParam String id, @Valid @RequestBody ProductUpdateRequest productCreateRequest) {
        return productService.update(id, productCreateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        productService.deleteById(id);
    }

    @PutMapping("/makeStatusActive")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto makeStatusActive(@RequestParam String id) {
        return productService.makeStatusActive(id);
    }

    @PutMapping("/makeStatusPassive")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto makeStatusPassive(@RequestParam String id) {
        return productService.makeStatusPassive(id);
    }
}