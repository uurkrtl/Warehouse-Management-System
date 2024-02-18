package net.ugurkartal.wmsservice.services.concretes;

import net.ugurkartal.wmsservice.core.utilities.mappers.ModelMapperService;
import net.ugurkartal.wmsservice.models.Category;
import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.repositories.CategoryRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.dtos.CategoryDto;
import net.ugurkartal.wmsservice.services.requests.CategoryCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryManagerTest {
    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;
    private GenerateIDService generateIDService;
    private CategoryManager categoryManager;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        modelMapperService = mock(ModelMapperService.class);
        categoryRepository = mock(CategoryRepository.class);
        generateIDService = mock(GenerateIDService.class);
        categoryManager = new CategoryManager(categoryRepository, modelMapperService, generateIDService);
        when(modelMapperService.forDto()).thenReturn(modelMapper);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
    }

    @Test
    void getAllCategoriesReturnsExpectedList() {
        // GIVEN
        Category category1 = new Category("1", "Category 1", "Description 1", null, null, true);
        Category category2 = new Category("2", "Category 2", "Description 2", null, null, true);
        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

        CategoryDto categoryDto1 = new CategoryDto("1", "Category 1", "Description 1", null, null, true);
        CategoryDto categoryDto2 = new CategoryDto("2", "Category 23", "Description 2", null, null, true);
        List<CategoryDto> expected = List.of(categoryDto1, categoryDto2);

        when(modelMapperService.forDto().map(category1, CategoryDto.class)).thenReturn(categoryDto1);
        when(modelMapperService.forDto().map(category2, CategoryDto.class)).thenReturn(categoryDto2);

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

        CategoryDto expected = new CategoryDto("1", "Category 1", "Description 1", null, null, true);
        when(modelMapperService.forDto().map(category, CategoryDto.class)).thenReturn(expected);

        // WHEN
        CategoryDto actual = categoryManager.getById("1");

        // THEN
        assertEquals(expected, actual);
        verify(categoryRepository, times(1)).findById("1");
    }

    @Test
    void addCategoryReturnsNewCategory() {
        // GIVEN
        Category category = new Category("1", "Category 1", "Description 1", null, null, true);
        CategoryCreateRequest categoryCreateRequest = new CategoryCreateRequest("Category 1", "Description 1");
        when(modelMapperService.forRequest().map(categoryCreateRequest, Category.class)).thenReturn(category);
        CategoryDto expected = new CategoryDto("1", "Category 1", "Description 1", null, null, true);
        when(modelMapperService.forDto().map(category, CategoryDto.class)).thenReturn(expected);
        //when(generateIDService.generateCategoryId()).thenReturn("1");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // WHEN
        CategoryDto actual = categoryManager.add(categoryCreateRequest);

        // THEN
        assertEquals(expected, actual);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}