package net.ugurkartal.wmsservice.services.validations;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.exception.DuplicateRecordException;
import net.ugurkartal.wmsservice.core.exception.RecordNotFoundException;
import net.ugurkartal.wmsservice.core.exception.StockNotZeroException;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductValidation {
    private final ProductRepository productRepository;

    public void checkIfProductByIdNotFound(String id) {
        if(!this.productRepository.existsById(id)) {
            throw new RecordNotFoundException("Product not found. Searched ID: " + id);
        }
    }

    public void checkIfProductNameExists(String productName) {
        if(this.productRepository.existsByName(productName)) {
            throw new DuplicateRecordException("Product name already exists: " + productName);
        }
    }

    public void checkIfProductNameExists(String productName, String id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            if(!product.getName().equals(productName) && this.productRepository.existsByName(productName)) {
                throw new DuplicateRecordException("Product name already exists: " + productName);
            }
        }
    }

    public void checkIfProductStockNotZero(String id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            if(product.getStock() > 0) {
                throw new StockNotZeroException("Product stock is not zero. Product name: " + product.getName());
            }
        }
    }
}