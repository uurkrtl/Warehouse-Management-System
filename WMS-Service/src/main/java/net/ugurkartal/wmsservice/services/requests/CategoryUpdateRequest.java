package net.ugurkartal.wmsservice.services.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {
    private String name;
    private String description;
    private boolean isActive;
}
