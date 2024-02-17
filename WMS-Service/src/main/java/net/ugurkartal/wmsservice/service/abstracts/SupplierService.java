package net.ugurkartal.wmsservice.service.abstracts;

import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.service.dtos.SupplierDto;
import net.ugurkartal.wmsservice.service.requests.SupplierCreateRequest;
import net.ugurkartal.wmsservice.service.requests.SupplierUpdateRequest;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> getAll();
    SupplierDto getById(String id);
    Supplier add(SupplierCreateRequest supplierCreateRequest);
    Supplier update(String id, SupplierUpdateRequest supplierUpdateRequest);
    boolean deleteById(String id);
}
