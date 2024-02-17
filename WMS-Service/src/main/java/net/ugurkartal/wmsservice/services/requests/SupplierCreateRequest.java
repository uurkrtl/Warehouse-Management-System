package net.ugurkartal.wmsservice.services.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierCreateRequest {
    private String name;
    private String contactName;
    private String email;
    private String phone;
}