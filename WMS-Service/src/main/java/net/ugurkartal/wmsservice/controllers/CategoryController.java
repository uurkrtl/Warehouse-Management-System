package net.ugurkartal.wmsservice.controllers;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.services.abstracts.CategoryService;
import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import net.ugurkartal.wmsservice.services.requests.CategoryUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getById(@PathVariable String id) {
        return categoryService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto add(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        return categoryService.add(categoryCreateRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@RequestParam String id, @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        return categoryService.update(id, categoryUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        categoryService.deleteById(id);
    }
}
