package net.ugurkartal.wmsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double salePrice;
    private int stock;
    private int criticalStock;
    private String imageUrl;
    @DBRef
    private Category category;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean isActive;
}