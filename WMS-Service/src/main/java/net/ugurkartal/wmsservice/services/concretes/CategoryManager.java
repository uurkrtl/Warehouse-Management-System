package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.utilities.mappers.ModelMapperService;
import net.ugurkartal.wmsservice.models.Category;
import net.ugurkartal.wmsservice.repositories.CategoryRepository;
import net.ugurkartal.wmsservice.services.abstracts.CategoryService;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import net.ugurkartal.wmsservice.services.requests.CategoryUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;
    private final GenerateIDService generateIDService;
    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(category -> this.modelMapperService.forDto().map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        return this.modelMapperService.forDto().map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto add(CategoryCreateRequest categoryCreateRequest) {
        Category category = this.modelMapperService.forRequest().map(categoryCreateRequest, Category.class);
        return this.modelMapperService.forRequest().map(this.categoryRepository.save(category.withId(generateIDService.generateCategoryId()).withCreated_at(LocalDateTime.now()).withActive(true)), CategoryDto.class);
    }

    @Override
    public CategoryDto update(String id, CategoryUpdateRequest categoryUpdateRequest) {
        Category foundCategory = this.categoryRepository.findById(id).orElse(null);
        Category category = this.modelMapperService.forRequest().map(categoryUpdateRequest, Category.class);
        return this.modelMapperService.forRequest().map(this.categoryRepository.save(category.withId(id).withCreated_at(foundCategory.getCreated_at()).withUpdated_at(LocalDateTime.now())), CategoryDto.class);
    }

    @Override
    public boolean deleteById(String id) {
        this.categoryRepository.deleteById(id);
        return true;
    }
}
