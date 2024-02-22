package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.services.dtos.ProductDto;
import net.ugurkartal.wmsservice.services.requests.ProductCreateRequest;
import net.ugurkartal.wmsservice.services.requests.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(String id);
    ProductDto add(ProductCreateRequest productCreateRequest);
    ProductDto update(String id, ProductUpdateRequest productUpdateRequest);
    boolean deleteById(String id);
    ProductDto updateStock(String id, int quantity);
}
