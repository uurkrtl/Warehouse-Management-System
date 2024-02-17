package net.ugurkartal.wmsservice.service.abstracts;

import org.springframework.stereotype.Service;

@Service
public interface GenerateIDService {
    public String generateSupplierId();
    public String generateCategoryId();
    public String generateProductId();
    public String generatePurchaseId();
    public String generateOrderId();
    public String generateOrderDetailId();
}