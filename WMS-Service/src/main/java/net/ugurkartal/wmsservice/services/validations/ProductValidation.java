package net.ugurkartal.wmsservice.services.validations;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.exception.DuplicateRecordException;
import net.ugurkartal.wmsservice.core.exception.RecordNotFoundException;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

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
}
