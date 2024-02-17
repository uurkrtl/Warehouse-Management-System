package net.ugurkartal.wmsservice.service.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierUpdateRequest {
    private String name;
    private String contactName;
    private String email;
    private String phone;
    private boolean isActive;
}