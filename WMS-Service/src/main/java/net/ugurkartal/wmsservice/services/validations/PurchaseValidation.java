package net.ugurkartal.wmsservice.services.validations;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.exception.RecordNotFoundException;
import net.ugurkartal.wmsservice.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseValidation {
    private final PurchaseRepository purchaseRepository;

    public void checkIfPurchaseByIdNotFound(String id) {
        if(!this.purchaseRepository.existsById(id)) {
            throw new RecordNotFoundException("Purchase not found. Searched ID: " + id);
        }
    }
}
