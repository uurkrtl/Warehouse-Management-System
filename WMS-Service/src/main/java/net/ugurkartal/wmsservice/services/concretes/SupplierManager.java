package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.core.utilities.mappers.ModelMapperService;
import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.repositories.SupplierRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.SupplierService;
import net.ugurkartal.wmsservice.services.dtos.SupplierDto;
import net.ugurkartal.wmsservice.services.requests.SupplierCreateRequest;
import net.ugurkartal.wmsservice.services.requests.SupplierUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierManager implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapperService modelMapperService;
    private final GenerateIDService generateIDService;

    @Override
    public List<SupplierDto> getAll() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierDto> supplierDtoList = suppliers.stream().map(supplier -> this.modelMapperService.forDto().map(supplier, SupplierDto.class)).collect(Collectors.toList());
        return supplierDtoList;
    }

    @Override
    public SupplierDto getById(String id) {
        Supplier supplier = this.supplierRepository.findById(id).orElse(null);
        SupplierDto supplierDto = this.modelMapperService.forDto().map(supplier, SupplierDto.class);
        return supplierDto;
    }

    @Override
    public Supplier add(SupplierCreateRequest supplierCreateRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(supplierCreateRequest, Supplier.class);
        return this.supplierRepository.save(supplier.withId(generateIDService.generateSupplierId()).withCreatedAt(LocalDateTime.now()).withActive(true));
    }

    @Override
    public Supplier update(String id, SupplierUpdateRequest supplierUpdateRequest) {
        Supplier foundSupplier = this.supplierRepository.findById(id).orElse(null);
        Supplier supplier = modelMapperService.forRequest().map(supplierUpdateRequest, Supplier.class);
        return this.supplierRepository.save(supplier.withId(id).withCreatedAt(foundSupplier.getCreatedAt()).withUpdatedAt(LocalDateTime.now()));
    }

    @Override
    public boolean deleteById(String id) {
        Supplier supplier = this.supplierRepository.findById(id).orElse(null);
        this.supplierRepository.deleteById(id);
        return supplier != null ? true : false;
    }
}
