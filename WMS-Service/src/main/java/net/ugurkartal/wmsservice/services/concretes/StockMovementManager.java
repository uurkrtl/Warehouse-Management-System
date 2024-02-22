package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.models.StockMovement;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import net.ugurkartal.wmsservice.repositories.StockMovementRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.ProductService;
import net.ugurkartal.wmsservice.services.abstracts.StockMovementService;
import net.ugurkartal.wmsservice.services.dtos.StockMovementDto;
import net.ugurkartal.wmsservice.services.mappers.StockMovementMapper;
import net.ugurkartal.wmsservice.services.requests.StockMovementCreateRequest;
import net.ugurkartal.wmsservice.services.requests.StockMovementUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockMovementManager implements StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final GenerateIDService generateIDService;
    private final StockMovementMapper stockMovementMapper;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    public List<StockMovementDto> getAll() {
        List<StockMovement> stockMovements = this.stockMovementRepository.findAll();
        List<StockMovementDto> stockMovementDtos = stockMovements.stream().map(stockMovement -> stockMovementMapper.stockMovementToStockMovementDtoMapper(stockMovement)).collect(Collectors.toList());
        return stockMovementDtos;
    }

    @Override
    public StockMovementDto getById(String id) {
        StockMovement stockMovement = this.stockMovementRepository.findById(id).orElse(null);
        StockMovementDto stockMovementDto = this.stockMovementMapper.stockMovementToStockMovementDtoMapper(stockMovement);
        return stockMovementDto;
    }

    @Override
    public StockMovementDto add(StockMovementCreateRequest stockMovementCreateRequest) {
        if (!stockMovementCreateRequest.isType()) {
            stockMovementCreateRequest.setQuantity(stockMovementCreateRequest.getQuantity() * -1);
        }

        // Update stock in product class
        productService.updateStock(stockMovementCreateRequest.getProductId(), stockMovementCreateRequest.getQuantity());

        StockMovement stockMovement = this.stockMovementMapper.createRequestToStockMovementMapper(stockMovementCreateRequest);
        Product foundProduct = this.productRepository.findById(stockMovementCreateRequest.getProductId()).orElse(null);

        String newId = this.generateIDService.generateStockMovementId();
        stockMovement.setId(newId);
        stockMovement.setProduct(foundProduct);
        stockMovement.setCreated_at(LocalDateTime.now());

        stockMovement = this.stockMovementRepository.save(stockMovement);



        return this.stockMovementMapper.stockMovementToStockMovementDtoMapper(stockMovement);
    }

    @Override
    public StockMovementDto update(String id, StockMovementUpdateRequest stockMovementUpdateRequest) {
        StockMovement foundStockMovement = this.stockMovementRepository.findById(id).orElse(null);

        foundStockMovement.setQuantity(stockMovementUpdateRequest.getQuantity());
        foundStockMovement.setType(stockMovementUpdateRequest.isType());
        foundStockMovement.setReason(stockMovementUpdateRequest.getReason());

        foundStockMovement = this.stockMovementRepository.save(foundStockMovement);
        return this.stockMovementMapper.stockMovementToStockMovementDtoMapper(foundStockMovement);
    }

    @Override
    public boolean deleteById(String id) {
        this.stockMovementRepository.deleteById(id);
        return true;
    }
}