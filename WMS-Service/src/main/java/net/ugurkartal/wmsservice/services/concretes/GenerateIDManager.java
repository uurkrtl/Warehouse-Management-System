package net.ugurkartal.wmsservice.services.concretes;

import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GenerateIDManager implements GenerateIDService {
    @Override
    public String generateSupplierId() { return "SP-" + UUID.randomUUID(); }

    @Override
    public String generateCategoryId() {
        return "CT-" + UUID.randomUUID();
    }

    @Override
    public String generateProductId() {
        return "PR-" + UUID.randomUUID();
    }

    @Override
    public String generatePurchaseId() {
        return "PU-" + UUID.randomUUID();
    }

    @Override
    public String generateOrderId() {
        return "OR-" + UUID.randomUUID();
    }

    @Override
    public String generateOrderDetailId() {
        return "OD-" + UUID.randomUUID();
    }
}
