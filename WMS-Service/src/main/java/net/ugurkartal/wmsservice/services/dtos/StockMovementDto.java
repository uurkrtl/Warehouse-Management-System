package net.ugurkartal.wmsservice.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.models.enums.StockMovementReason;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDto {
    private String id;
    private Product product;
    private int quantity;
    private boolean type;
    private StockMovementReason reason;
    private LocalDateTime created_at;
}