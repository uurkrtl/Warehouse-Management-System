package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.StockMovement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockMovementRepository extends MongoRepository<StockMovement, String>{
}
