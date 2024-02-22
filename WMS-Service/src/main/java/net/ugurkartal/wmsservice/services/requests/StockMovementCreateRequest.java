package net.ugurkartal.wmsservice.services.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ugurkartal.wmsservice.models.enums.StockMovementReason;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementCreateRequest {
    private String productId;
    private int quantity;
    private boolean type;
    private StockMovementReason reason;
}