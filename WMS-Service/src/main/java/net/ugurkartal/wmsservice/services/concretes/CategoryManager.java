package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Category;
import net.ugurkartal.wmsservice.repositories.CategoryRepository;
import net.ugurkartal.wmsservice.services.abstracts.CategoryService;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.mappers.CategoryMapper;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import net.ugurkartal.wmsservice.services.requests.CategoryUpdateRequest;
import net.ugurkartal.wmsservice.services.validations.CategoryValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final GenerateIDService generateIDService;
    private final CategoryMapper categoryMapper;
    private final CategoryValidation categoryValidation;
    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> categoryMapper.categoryToCategoryDtoMapper(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getById(String id) {
        this.categoryValidation.checkIfCategoryByIdNotFound(id);
        Category category = this.categoryRepository.findById(id).orElse(null);
        CategoryDto categoryDto = this.categoryMapper.categoryToCategoryDtoMapper(category);
        return categoryDto;
    }

    @Override
    public CategoryDto add(CategoryCreateRequest categoryCreateRequest) {
        categoryValidation.checkIfCategoryNameExists(categoryCreateRequest.getName());
        Category category = this.categoryMapper.createRequestToCategoryMapper(categoryCreateRequest);

        String newId = this.generateIDService.generateCategoryId();
        category.setId(newId);
        category.setCreated_at(LocalDateTime.now());
        category.setActive(true);

        this.categoryRepository.save(category);
        Category newCategory = this.categoryRepository.findById(newId).orElse(null);
        CategoryDto categoryDto = this.categoryMapper.categoryToCategoryDtoMapper(newCategory);
        return categoryDto;
    }

    @Override
    public CategoryDto update(String id, CategoryUpdateRequest categoryUpdateRequest) {
        this.categoryValidation.checkIfCategoryByIdNotFound(id);
        Category updatedCategory = this.categoryMapper.updateRequestToCategoryMapper(categoryUpdateRequest);
        Category foundCategory = this.categoryRepository.findById(id).orElse(null);
        updatedCategory.setId(id);
        updatedCategory.setCreated_at(foundCategory.getCreated_at());
        updatedCategory.setUpdated_at(LocalDateTime.now());
        this.categoryRepository.save(updatedCategory);

        Category newCategory = this.categoryRepository.findById(id).orElse(null);
        CategoryDto categoryDto = this.categoryMapper.categoryToCategoryDtoMapper(newCategory);
        return categoryDto;
    }

    @Override
    public boolean deleteById(String id) {
        this.categoryValidation.checkIfCategoryByIdNotFound(id);
        this.categoryRepository.deleteById(id);
        return true;
    }
}