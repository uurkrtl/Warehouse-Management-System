package net.ugurkartal.wmsservice.services.concretes;

import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GenerateIDManager implements GenerateIDService {
    @Override
    public String generateSupplierId() { return "SUP-" + UUID.randomUUID(); }

    @Override
    public String generateCategoryId() {
        return "CAT-" + UUID.randomUUID();
    }

    @Override
    public String generateProductId() {
        return "PRD-" + UUID.randomUUID();
    }

    @Override
    public String generatePurchaseId() {
        return "PUR-" + UUID.randomUUID();
    }

    @Override
    public String generateOrderId() {
        return "ORD-" + UUID.randomUUID();
    }

    @Override
    public String generateOrderDetailId() {
        return "ODT-" + UUID.randomUUID();
    }

    @Override
    public String generateStockMovementId() {
        return "SMV-" + UUID.randomUUID();
    }
}