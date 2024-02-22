package net.ugurkartal.wmsservice.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ugurkartal.wmsservice.models.Order;
import net.ugurkartal.wmsservice.models.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private String id;
    private Order order;
    private Product product;
    private int quantity;
    private double price;
    private double totalPrice;
    private boolean isActive;
}