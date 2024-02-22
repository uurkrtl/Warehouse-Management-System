package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.models.Purchase;
import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import net.ugurkartal.wmsservice.repositories.PurchaseRepository;
import net.ugurkartal.wmsservice.repositories.SupplierRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.PurchaseService;
import net.ugurkartal.wmsservice.services.dtos.PurchaseDto;
import net.ugurkartal.wmsservice.services.mappers.PurchaseMapper;
import net.ugurkartal.wmsservice.services.requests.PurchaseCreateRequest;
import net.ugurkartal.wmsservice.services.requests.PurchaseUpdateRequest;
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

    @Override
    public List<PurchaseDto> getAll() {
        List<Purchase> purchases = this.purchaseRepository.findAll();
        List<PurchaseDto> purchaseDtos = purchases.stream().map(purchase -> purchaseMapper.purchaseToPurchaseDtoMapper(purchase)).collect(Collectors.toList());
        return purchaseDtos;
    }

    @Override
    public PurchaseDto getById(String id) {
        Purchase purchase = this.purchaseRepository.findById(id).orElse(null);
        PurchaseDto purchaseDto = this.purchaseMapper.purchaseToPurchaseDtoMapper(purchase);
        return purchaseDto;
    }

    @Override
    public PurchaseDto add(PurchaseCreateRequest purchaseCreateRequest) {
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
        return this.purchaseMapper.purchaseToPurchaseDtoMapper(purchase);
    }

    @Override
    public PurchaseDto update(String id, PurchaseUpdateRequest purchaseUpdateRequest) {
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
        this.purchaseRepository.deleteById(id);
        return true;
    }
}