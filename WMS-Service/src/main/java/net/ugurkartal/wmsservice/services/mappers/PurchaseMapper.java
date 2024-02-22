package net.ugurkartal.wmsservice.services.mappers;

import net.ugurkartal.wmsservice.models.Purchase;
import net.ugurkartal.wmsservice.services.dtos.PurchaseDto;
import net.ugurkartal.wmsservice.services.requests.PurchaseCreateRequest;
import net.ugurkartal.wmsservice.services.requests.PurchaseUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class PurchaseMapper {
    public PurchaseDto purchaseToPurchaseDtoMapper(Purchase purchase) {
        return new PurchaseDto(purchase.getId(), purchase.getProduct(), purchase.getSupplier(), purchase.getPurchasePrice(), purchase.getQuantity(), purchase.getTotalPrice(), purchase.getPurchaseDate(), purchase.getCreated_at(), purchase.getUpdated_at(), purchase.isActive());
    }

    public Purchase createRequestToPurchaseMapper(PurchaseCreateRequest purchaseCreateRequest) {
        return Purchase.builder()
                .purchasePrice(purchaseCreateRequest.getPurchasePrice())
                .quantity(purchaseCreateRequest.getQuantity())
                .purchaseDate(purchaseCreateRequest.getPurchaseDate())
                .build();
    }

    public Purchase updateRequestToPurchaseMapper(PurchaseUpdateRequest purchaseUpdateRequest) {
        return Purchase.builder()
                .purchasePrice(purchaseUpdateRequest.getPurchasePrice())
                .quantity(purchaseUpdateRequest.getQuantity())
                .purchaseDate(purchaseUpdateRequest.getPurchaseDate())
                .isActive(purchaseUpdateRequest.isActive())
                .build();
    }
}