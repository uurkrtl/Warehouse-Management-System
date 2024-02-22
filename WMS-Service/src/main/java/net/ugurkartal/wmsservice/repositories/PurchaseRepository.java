package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String>{
}
