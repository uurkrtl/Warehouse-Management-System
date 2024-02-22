package net.ugurkartal.wmsservice.services.mappers;

import net.ugurkartal.wmsservice.models.StockMovement;
import net.ugurkartal.wmsservice.services.dtos.StockMovementDto;
import net.ugurkartal.wmsservice.services.requests.StockMovementCreateRequest;
import net.ugurkartal.wmsservice.services.requests.StockMovementUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class StockMovementMapper {
    public StockMovementDto stockMovementToStockMovementDtoMapper(StockMovement stockMovement) {
        return new StockMovementDto(stockMovement.getId(), stockMovement.getProduct(), stockMovement.getQuantity(), stockMovement.isType(), stockMovement.getReason(), stockMovement.getCreated_at());
    }

    public StockMovement createRequestToStockMovementMapper(StockMovementCreateRequest stockMovementCreateRequest) {
        return StockMovement.builder()
                .quantity(stockMovementCreateRequest.getQuantity())
                .type(stockMovementCreateRequest.isType())
                .reason(stockMovementCreateRequest.getReason())
                .build();
    }

    public StockMovement updateRequestToStockMovementMapper(StockMovementUpdateRequest stockMovementUpdateRequest) {
        return StockMovement.builder()
                .quantity(stockMovementUpdateRequest.getQuantity())
                .type(stockMovementUpdateRequest.isType())
                .reason(stockMovementUpdateRequest.getReason())
                .build();
    }
}