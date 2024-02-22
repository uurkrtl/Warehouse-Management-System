package net.ugurkartal.wmsservice.services.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailCreateRequest {
    private String orderId;
    private String productId;
    private int quantity;
}