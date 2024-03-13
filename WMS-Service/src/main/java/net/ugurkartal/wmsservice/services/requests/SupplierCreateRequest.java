package net.ugurkartal.wmsservice.services.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierCreateRequest {
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Size(min = 3, max = 50, message = "Contact name must be between 3 and 50 characters")
    private String contactName;
    @Email(message = "Email must be valid")
    private String email;
    @Size(min = 10, max = 20, message = "Phone must be between 10 and 20 characters")
    private String phone;
}