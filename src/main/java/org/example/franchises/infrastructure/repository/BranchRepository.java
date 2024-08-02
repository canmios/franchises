package org.example.franchises.infrastructure.repository;

import org.example.franchises.domain.model.Branch;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchRepository extends ReactiveMongoRepository<Branch, String> {
    Mono<Branch> findByFranchiseIdAndBranchId(String franchiseId, String branchId);

    Flux<Branch> findByFranchiseId(String franchiseId);
}

