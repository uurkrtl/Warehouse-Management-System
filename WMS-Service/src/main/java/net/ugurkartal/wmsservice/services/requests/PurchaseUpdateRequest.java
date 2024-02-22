package net.ugurkartal.wmsservice.services.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseUpdateRequest {
    private String productId;
    private String supplierId;
    private double purchasePrice;
    private int quantity;
    private LocalDate purchaseDate;
    private boolean isActive;
}
