package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>{
}
