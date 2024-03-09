package net.ugurkartal.wmsservice.services.mappers;

import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.services.dtos.ProductDto;
import net.ugurkartal.wmsservice.services.requests.ProductCreateRequest;
import net.ugurkartal.wmsservice.services.requests.ProductUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductDto productToProductDtoMapper(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getSalePrice(), product.getStock(), product.getCriticalStock(), product.getImageUrl(), product.getCategory().getName(), product.getCreatedAt(), product.getUpdatedAt(), product.isActive());
    }

    public Product createRequestToProductMapper(ProductCreateRequest productCreateRequest) {
        return new Product("0", productCreateRequest.getName(), productCreateRequest.getDescription(), productCreateRequest.getSalePrice(), productCreateRequest.getStock(), productCreateRequest.getCriticalStock(), productCreateRequest.getImageUrl(), null, null, null, true);
    }

    public Product updateRequestToProductMapper(ProductUpdateRequest productUpdateRequest) {
        return new Product("0", productUpdateRequest.getName(), productUpdateRequest.getDescription(), productUpdateRequest.getSalePrice(), productUpdateRequest.getStock(), productUpdateRequest.getCriticalStock(), productUpdateRequest.getImageUrl(), null, null, null, productUpdateRequest.isActive());
    }
}
