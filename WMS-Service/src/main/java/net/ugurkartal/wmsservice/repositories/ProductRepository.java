package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{
}
