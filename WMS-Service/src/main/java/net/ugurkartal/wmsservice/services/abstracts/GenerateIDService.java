package net.ugurkartal.wmsservice.services.abstracts;

import org.springframework.stereotype.Service;

@Service
public interface GenerateIDService {
    String generateSupplierId();
    String generateCategoryId();
    String generateProductId();
    String generatePurchaseId();
    String generateOrderId();
    String generateOrderDetailId();
    String generateStockMovementId();
}