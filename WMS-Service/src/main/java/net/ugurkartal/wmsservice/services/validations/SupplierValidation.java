package net.ugurkartal.wmsservice.services.validations;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.exception.DuplicateRecordException;
import net.ugurkartal.wmsservice.core.exception.RecordNotFoundException;
import net.ugurkartal.wmsservice.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierValidation {
    private final SupplierRepository supplierRepository;

    public void checkIfSupplierByIdNotFound(String id) {
        if(!this.supplierRepository.existsById(id)) {
            throw new RecordNotFoundException("Supplier not found. Searched ID: " + id);
        }
    }

    public void checkIfSupplierNameExists(String supplierName) {
        if(this.supplierRepository.existsByName(supplierName)) {
            throw new DuplicateRecordException("Supplier name already exists: " + supplierName);
        }
    }
}
