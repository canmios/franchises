package org.example.franchises.infrastructure.repository;

import org.example.franchises.domain.model.Franchise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FranchiseRepository extends ReactiveMongoRepository<Franchise, String> {
}

