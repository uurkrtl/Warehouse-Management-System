package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, String>{
}
