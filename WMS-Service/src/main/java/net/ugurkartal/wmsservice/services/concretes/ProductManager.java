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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final GenerateIDService generateIDService;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = this.productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> productMapper.productToProductDtoMapper(product)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public ProductDto getById(String id) {
        Product product = this.productRepository.findById(id).orElse(null);
        ProductDto productDto = this.productMapper.productToProductDtoMapper(product);
        return productDto;
    }

    @Override
    public ProductDto add(ProductCreateRequest productCreateRequest) {
        Product product = this.productMapper.createRequestToProductMapper(productCreateRequest);
        Category foundCategory = this.categoryRepository.findById(productCreateRequest.getCategoryId()).orElse(null);

        String newId = this.generateIDService.generateProductId();
        product.setId(newId);
        product.setActive(true);
        product.setCreated_at(LocalDateTime.now());
        product.setCategory(foundCategory);

        product = this.productRepository.save(product);
        return this.productMapper.productToProductDtoMapper(product);
    }

    @Override
    public ProductDto update(String id, ProductUpdateRequest productUpdateRequest) {
        Product updatedProduct = this.productMapper.updateRequestToProductMapper(productUpdateRequest);
        Product foundProduct = this.productRepository.findById(id).orElse(null);
        Category foundCategory = this.categoryRepository.findById(productUpdateRequest.getCategoryId()).orElse(null);

        updatedProduct.setId(id);
        updatedProduct.setCreated_at(foundProduct.getCreated_at());
        updatedProduct.setUpdated_at(LocalDateTime.now());
        updatedProduct.setCategory(foundCategory);
        updatedProduct = this.productRepository.save(updatedProduct);

        return this.productMapper.productToProductDtoMapper(updatedProduct);
    }

    @Override
    public boolean deleteById(String id) {
        this.categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductDto updateStock(String id, int quantity) {
        Product foundProduct = this.productRepository.findById(id).orElse(null);
        foundProduct.setStock(foundProduct.getStock() + quantity);
        foundProduct = this.productRepository.save(foundProduct);
        return this.productMapper.productToProductDtoMapper(foundProduct);
    }
}