package org.example.franchises.infrastructure.repository;

import org.example.franchises.domain.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}

