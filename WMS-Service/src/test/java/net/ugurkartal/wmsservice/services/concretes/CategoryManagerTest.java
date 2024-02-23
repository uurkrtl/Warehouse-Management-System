package net.ugurkartal.wmsservice.services.concretes;

import net.ugurkartal.wmsservice.models.Category;
import net.ugurkartal.wmsservice.repositories.CategoryRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.mappers.CategoryMapper;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import net.ugurkartal.wmsservice.services.requests.CategoryUpdateRequest;
import net.ugurkartal.wmsservice.services.validations.CategoryValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryManagerTest {
    private CategoryRepository categoryRepository;
    private GenerateIDService generateIDService;
    private CategoryManager categoryManager;
    private CategoryMapper categoryMapper;
    private CategoryValidation categoryValidation;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        generateIDService = mock(GenerateIDService.class);
        categoryMapper = new CategoryMapper();
        categoryValidation = mock(CategoryValidation.class);
        categoryManager = new CategoryManager(categoryRepository, generateIDService, categoryMapper, categoryValidation);
    }

    @Test
    void getAllCategoriesReturnsExpectedList() {
        // GIVEN
        Category category1 = new Category("1", "Category 1", "Description 1", null, null, true);
        Category category2 = new Category("2", "Category 2", "Description 2", null, null, true);
        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

        CategoryDto categoryDto1 = categoryMapper.categoryToCategoryDtoMapper(category1);
        CategoryDto categoryDto2 = categoryMapper.categoryToCategoryDtoMapper(category2);
        List<CategoryDto> expected = List.of(categoryDto1, categoryDto2);

        // WHEN
        List<CategoryDto> actual = categoryManager.getAll();

        // THEN
        assertEquals(expected, actual);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getByIdCategoryReturnsExpectedCategory() {
        // GIVEN
        Category category = new Category("1", "Category 1", "Description 1", null, null, true);
        when(categoryRepository.findById("1")).thenReturn(Optional.of(category));
        CategoryDto expected = categoryMapper.categoryToCategoryDtoMapper(category);

        // WHEN
        CategoryDto actual = categoryManager.getById("1");

        // THEN
        assertEquals(expected, actual);
        verify(categoryRepository, times(1)).findById("1");
    }

    @Test
    void addCategoryReturnsNewCategory() {
        // GIVEN
        CategoryCreateRequest categoryCreateRequest = new CategoryCreateRequest("Category 1", "Description 1");
        Category category = categoryMapper.createRequestToCategoryMapper(categoryCreateRequest);
        when(generateIDService.generateCategoryId()).thenReturn("1");
        when(categoryRepository.save(category)).thenReturn(category);
        CategoryDto expected = categoryMapper.categoryToCategoryDtoMapper(category);
        when(categoryRepository.findById("1")).thenReturn(Optional.of(category));

        // WHEN
        CategoryDto actual = categoryManager.add(categoryCreateRequest);

        // THEN
        assertEquals(expected, actual);
        verify(generateIDService, times(1)).generateCategoryId();
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategoryReturnsUpdatedCategory() {
        // GIVEN
        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest("Category 1", "Description 1", true);
        Category updatedCategory = categoryMapper.updateRequestToCategoryMapper(categoryUpdateRequest);
        Category foundCategory = new Category("1", "Category 1", "Description 1", null, null, true);
        when(categoryRepository.findById("1")).thenReturn(Optional.of(foundCategory));
        when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);
        CategoryDto expected = categoryMapper.categoryToCategoryDtoMapper(updatedCategory.withId("1"));

        // WHEN
        CategoryDto actual = categoryManager.update("1", categoryUpdateRequest);

        // THEN
        assertEquals(expected, actual);
        verify(categoryRepository, times(2)).findById("1");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void deleteCategoryByIdReturnsTrue() {
        // GIVEN
        doNothing().when(categoryRepository).deleteById("1");

        // WHEN
        boolean actual = categoryManager.deleteById("1");

        // THEN
        assertTrue(actual);
        verify(categoryRepository, times(1)).deleteById("1");
    }
}