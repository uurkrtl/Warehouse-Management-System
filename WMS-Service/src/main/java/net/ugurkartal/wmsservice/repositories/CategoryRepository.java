package net.ugurkartal.wmsservice.repositories;

import net.ugurkartal.wmsservice.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String>{
}
