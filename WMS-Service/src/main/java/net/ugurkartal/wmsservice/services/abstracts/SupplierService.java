package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.services.dtos.SupplierDto;
import net.ugurkartal.wmsservice.services.requests.SupplierCreateRequest;
import net.ugurkartal.wmsservice.services.requests.SupplierUpdateRequest;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> getAll();
    SupplierDto getById(String id);
    Supplier add(SupplierCreateRequest supplierCreateRequest);
    Supplier update(String id, SupplierUpdateRequest supplierUpdateRequest);
    boolean deleteById(String id);
}
