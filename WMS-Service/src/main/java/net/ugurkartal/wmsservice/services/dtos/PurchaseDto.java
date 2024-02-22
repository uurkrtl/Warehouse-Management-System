package net.ugurkartal.wmsservice.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.models.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private String id;
    private Product product;
    private Supplier supplier;
    private double purchasePrice;
    private int quantity;
    private double totalPrice;
    private LocalDate purchaseDate;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean isActive;
}
