package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import net.ugurkartal.wmsservice.services.requests.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(String id);
    CategoryDto add(CategoryCreateRequest categoryCreateRequest);
    CategoryDto update(String id, CategoryUpdateRequest categoryUpdateRequest);
    boolean deleteById(String id);
}
