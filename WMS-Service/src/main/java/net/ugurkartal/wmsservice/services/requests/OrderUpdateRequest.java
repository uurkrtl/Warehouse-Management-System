package net.ugurkartal.wmsservice.services.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ugurkartal.wmsservice.models.enums.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
    private OrderStatus orderStatus;
}