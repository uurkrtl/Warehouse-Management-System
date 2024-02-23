package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.models.Purchase;
import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.models.enums.StockMovementReason;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import net.ugurkartal.wmsservice.repositories.PurchaseRepository;
import net.ugurkartal.wmsservice.repositories.SupplierRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.PurchaseService;
import net.ugurkartal.wmsservice.services.abstracts.StockMovementService;
import net.ugurkartal.wmsservice.services.dtos.PurchaseDto;
import net.ugurkartal.wmsservice.services.mappers.PurchaseMapper;
import net.ugurkartal.wmsservice.services.requests.PurchaseCreateRequest;
import net.ugurkartal.wmsservice.services.requests.PurchaseUpdateRequest;
import net.ugurkartal.wmsservice.services.requests.StockMovementCreateRequest;
import net.ugurkartal.wmsservice.services.validations.ProductValidation;
import net.ugurkartal.wmsservice.services.validations.PurchaseValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseManager implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final GenerateIDService generateIDService;
    private final PurchaseMapper purchaseMapper;
    private final StockMovementService stockMovementService;
    private final PurchaseValidation purchaseValidation;
    private final ProductValidation productValidation;

    @Override
    public List<PurchaseDto> getAll() {
        List<Purchase> purchases = this.purchaseRepository.findAll();
        List<PurchaseDto> purchaseDtos = purchases.stream().map(purchase -> purchaseMapper.purchaseToPurchaseDtoMapper(purchase)).collect(Collectors.toList());
        return purchaseDtos;
    }

    @Override
    public PurchaseDto getById(String id) {
        this.purchaseValidation.checkIfPurchaseByIdNotFound(id);
        Purchase purchase = this.purchaseRepository.findById(id).orElse(null);
        PurchaseDto purchaseDto = this.purchaseMapper.purchaseToPurchaseDtoMapper(purchase);
        return purchaseDto;
    }

    @Override
    public PurchaseDto add(PurchaseCreateRequest purchaseCreateRequest) {
        this.productValidation.checkIfProductByIdNotFound(purchaseCreateRequest.getProductId());
        Purchase purchase = this.purchaseMapper.createRequestToPurchaseMapper(purchaseCreateRequest);
        Product foundProduct = this.productRepository.findById(purchaseCreateRequest.getProductId()).orElse(null);
        Supplier foundSupplier = this.supplierRepository.findById(purchaseCreateRequest.getSupplierId()).orElse(null);
        String newId = this.generateIDService.generatePurchaseId();

        purchase.setId(newId);
        purchase.setProduct(foundProduct);
        purchase.setSupplier(foundSupplier);
        purchase.setTotalPrice(purchaseCreateRequest.getPurchasePrice() * purchaseCreateRequest.getQuantity());
        purchase.setCreated_at(LocalDateTime.now());
        purchase.setActive(true);

        purchase = this.purchaseRepository.save(purchase);

        StockMovementCreateRequest stockMovementCreateRequest = StockMovementCreateRequest.builder()
                .productId(purchaseCreateRequest.getProductId())
                .quantity(purchaseCreateRequest.getQuantity())
                .type(true)
                .reason(StockMovementReason.PURCHASE)
                .build();
        stockMovementService.add(stockMovementCreateRequest);

        return this.purchaseMapper.purchaseToPurchaseDtoMapper(purchase);
    }

    @Override
    public PurchaseDto update(String id, PurchaseUpdateRequest purchaseUpdateRequest) {
        this.productValidation.checkIfProductByIdNotFound(purchaseUpdateRequest.getProductId());
        this.purchaseValidation.checkIfPurchaseByIdNotFound(id);
        Purchase updatedPurchase = this.purchaseMapper.updateRequestToPurchaseMapper(purchaseUpdateRequest);
        Purchase foundPurchase = this.purchaseRepository.findById(id).orElse(null);
        Product foundProduct = this.productRepository.findById(purchaseUpdateRequest.getProductId()).orElse(null);
        Supplier foundSupplier = this.supplierRepository.findById(purchaseUpdateRequest.getSupplierId()).orElse(null);

        updatedPurchase.setProduct(foundProduct);
        updatedPurchase.setSupplier(foundSupplier);
        updatedPurchase.setTotalPrice(purchaseUpdateRequest.getPurchasePrice() * purchaseUpdateRequest.getQuantity());
        updatedPurchase.setCreated_at(foundPurchase.getCreated_at());
        updatedPurchase.setUpdated_at(LocalDateTime.now());

        updatedPurchase = this.purchaseRepository.save(updatedPurchase);
        return this.purchaseMapper.purchaseToPurchaseDtoMapper(updatedPurchase);
    }

    @Override
    public boolean deleteById(String id) {
        this.purchaseValidation.checkIfPurchaseByIdNotFound(id);
        this.purchaseRepository.deleteById(id);
        return true;
    }
}