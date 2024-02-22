package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.services.dtos.PurchaseDto;
import net.ugurkartal.wmsservice.services.requests.PurchaseCreateRequest;
import net.ugurkartal.wmsservice.services.requests.PurchaseUpdateRequest;

import java.util.List;

public interface PurchaseService {
    List<PurchaseDto> getAll();
    PurchaseDto getById(String id);
    PurchaseDto add(PurchaseCreateRequest purchaseCreateRequest);
    PurchaseDto update(String id, PurchaseUpdateRequest purchaseUpdateRequest);
    boolean deleteById(String id);
}
