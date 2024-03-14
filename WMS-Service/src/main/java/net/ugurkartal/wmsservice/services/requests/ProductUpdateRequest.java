package net.ugurkartal.wmsservice.services.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Size(min = 3, max = 150, message = "Description must be between 3 and 50 characters")
    private String description;
    @Positive(message = "Sale price must be greater than 0")
    private double salePrice;
    @PositiveOrZero(message = "Stock must be greater than or equal to 0")
    private int stock;
    @PositiveOrZero(message = "Critical stock must be greater than or equal to 0")
    private int criticalStock;
    private String imageUrl;
    @NotNull(message = "Category id cannot be null")
    private String categoryId;
}