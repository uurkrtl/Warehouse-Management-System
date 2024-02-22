package net.ugurkartal.wmsservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class Purchase {
    @Id
    private String id;
    @DBRef
    private Product product;
    @DBRef
    private Supplier supplier;
    private double purchasePrice;
    private int quantity;
    private double totalPrice;
    private LocalDate purchaseDate;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean isActive;
}
