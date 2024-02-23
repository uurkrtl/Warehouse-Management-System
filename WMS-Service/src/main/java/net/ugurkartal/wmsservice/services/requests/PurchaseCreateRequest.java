package net.ugurkartal.wmsservice.services.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseCreateRequest {
    @NotNull(message = "Product id cannot be null")
    private String productId;
    @NotNull(message = "Supplier id cannot be null")
    private String supplierId;
    @Positive(message = "Purchase price must be greater than 0")
    private double purchasePrice;
    @Positive(message = "Quantity must be greater than 0")
    private int quantity;
    @NotNull(message = "Purchase date cannot be null")
    private LocalDate purchaseDate;
}