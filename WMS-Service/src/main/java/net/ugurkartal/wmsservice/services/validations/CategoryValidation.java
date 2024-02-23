package net.ugurkartal.wmsservice.services.validations;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.exception.DuplicateRecordException;
import net.ugurkartal.wmsservice.core.exception.RecordNotFoundException;
import net.ugurkartal.wmsservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryValidation {
    private final CategoryRepository categoryRepository;

    public void checkIfCategoryByIdNotFound(String id) {
        if(!this.categoryRepository.existsById(id)) {
            throw new RecordNotFoundException("Category not found. Searched ID: " + id);
        }
    }

    public void checkIfCategoryNameExists(String categoryName) {
        if(this.categoryRepository.existsByName(categoryName)) {
            throw new DuplicateRecordException("Category name already exists: " + categoryName);
        }
    }
}