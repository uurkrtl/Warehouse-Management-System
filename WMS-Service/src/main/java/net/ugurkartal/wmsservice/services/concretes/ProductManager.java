package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Category;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.repositories.CategoryRepository;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.ProductService;
import net.ugurkartal.wmsservice.services.dtos.ProductDto;
import net.ugurkartal.wmsservice.services.mappers.ProductMapper;
import net.ugurkartal.wmsservice.services.requests.ProductCreateRequest;
import net.ugurkartal.wmsservice.services.requests.ProductUpdateRequest;
import net.ugurkartal.wmsservice.services.validations.CategoryValidation;
import net.ugurkartal.wmsservice.services.validations.ProductValidation;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final GenerateIDService generateIDService;
    private final ProductMapper productMapper;
    private final ProductValidation productValidation;
    private final CategoryValidation categoryValidation;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = this.productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return products.stream().map(productMapper::productToProductDtoMapper).toList();
    }

    @Override
    public ProductDto getById(String id) {
        this.productValidation.checkIfProductByIdNotFound(id);
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            return this.productMapper.productToProductDtoMapper(product);
        } else {
            return null;
        }
    }

    @Override
    public ProductDto add(ProductCreateRequest productCreateRequest) {
        this.productValidation.checkIfProductNameExists(productCreateRequest.getName());
        this.categoryValidation.checkIfCategoryByIdNotFound(productCreateRequest.getCategoryId());
        Product product = this.productMapper.createRequestToProductMapper(productCreateRequest);
        Category foundCategory = this.categoryRepository.findById(productCreateRequest.getCategoryId()).orElse(null);

        String newId = this.generateIDService.generateProductId();
        product.setId(newId);
        product.setActive(true);
        product.setCreatedAt(LocalDateTime.now());
        product.setCategory(foundCategory);

        product = this.productRepository.save(product);
        return this.productMapper.productToProductDtoMapper(product);
    }

    @Override
    public ProductDto update(String id, ProductUpdateRequest productUpdateRequest) {
        this.productValidation.checkIfProductByIdNotFound(id);
        this.productValidation.checkIfProductNameExists(productUpdateRequest.getName(), id);
        this.categoryValidation.checkIfCategoryByIdNotFound(productUpdateRequest.getCategoryId());
        Product updatedProduct = this.productMapper.updateRequestToProductMapper(productUpdateRequest);
        Optional<Product> foundProductOptional = this.productRepository.findById(id);
        Category foundCategory = this.categoryRepository.findById(productUpdateRequest.getCategoryId()).orElse(null);

        if(foundProductOptional.isPresent()) {
            Product foundProduct = foundProductOptional.get();
            updatedProduct.setCreatedAt(foundProduct.getCreatedAt());
        } else {
            updatedProduct.setCreatedAt(LocalDateTime.now());
        }

        updatedProduct.setId(id);
        updatedProduct.setUpdatedAt(LocalDateTime.now());
        updatedProduct.setCategory(foundCategory);
        updatedProduct = this.productRepository.save(updatedProduct);

        return this.productMapper.productToProductDtoMapper(updatedProduct);
    }

    @Override
    public boolean deleteById(String id) {
        this.productValidation.checkIfProductByIdNotFound(id);
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductDto updateStock(String id, int quantity) {
        this.productValidation.checkIfProductByIdNotFound(id);
        Optional<Product> foundProductOptional = this.productRepository.findById(id);
        if (foundProductOptional.isPresent()) {
            Product foundProduct = foundProductOptional.get();
            foundProduct.setStock(foundProduct.getStock() - quantity);
            foundProduct = this.productRepository.save(foundProduct);
            return this.productMapper.productToProductDtoMapper(foundProduct);
        }else {
            return null;
        }
    }
}