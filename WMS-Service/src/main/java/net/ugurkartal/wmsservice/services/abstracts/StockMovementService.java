package net.ugurkartal.wmsservice.services.abstracts;

import net.ugurkartal.wmsservice.services.dtos.StockMovementDto;
import net.ugurkartal.wmsservice.services.requests.StockMovementCreateRequest;
import net.ugurkartal.wmsservice.services.requests.StockMovementUpdateRequest;

import java.util.List;

public interface StockMovementService {
    List<StockMovementDto> getAll();
    StockMovementDto getById(String id);
    StockMovementDto add(StockMovementCreateRequest stockMovementCreateRequest);
    StockMovementDto update(String id, StockMovementUpdateRequest stockMovementUpdateRequest);
    boolean deleteById(String id);
}