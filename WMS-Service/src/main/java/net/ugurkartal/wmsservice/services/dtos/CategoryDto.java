package net.ugurkartal.wmsservice.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private String name;
    private String description;
    private String created_at;
    private String updated_at;
    private boolean isActive;
}
