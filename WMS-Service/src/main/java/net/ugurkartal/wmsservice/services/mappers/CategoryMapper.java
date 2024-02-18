package net.ugurkartal.wmsservice.services.mappers;

import net.ugurkartal.wmsservice.models.Category;
import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import net.ugurkartal.wmsservice.services.requests.CategoryUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryDto categoryToCategoryDtoMapper(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getDescription(), category.getCreated_at(), category.getUpdated_at(), category.isActive());
    }

    public Category createRequestToCategoryMapper(CategoryCreateRequest categoryCreateRequest) {
        return new Category("0", categoryCreateRequest.getName(), categoryCreateRequest.getDescription(), null, null, true);
    }

    public Category updateRequestToCategoryMapper(CategoryUpdateRequest categoryUpdateRequest) {
        return new Category("0", categoryUpdateRequest.getName(), categoryUpdateRequest.getDescription(), null, null, categoryUpdateRequest.isActive());
    }
}
