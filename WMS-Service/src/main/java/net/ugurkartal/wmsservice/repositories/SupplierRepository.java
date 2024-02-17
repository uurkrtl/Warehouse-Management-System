package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String > {
}
