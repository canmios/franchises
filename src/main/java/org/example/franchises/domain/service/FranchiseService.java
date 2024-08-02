package org.example.franchises.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.franchises.application.port.in.controllers.mappers.FranchiseMapper;
import org.example.franchises.application.port.in.controllers.models.BranchRequest;
import org.example.franchises.application.port.in.controllers.models.FranchiseRequest;
import org.example.franchises.application.port.in.controllers.models.ProductRequest;
import org.example.franchises.domain.model.Franchise;
import org.example.franchises.infrastructure.repository.BranchRepository;
import org.example.franchises.infrastructure.repository.FranchiseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private static final FranchiseMapper mapper = FranchiseMapper.INSTANCE;

    public Mono<FranchiseRequest> addFranchise(FranchiseRequest franchiseRequest) {
        Franchise franchise = mapper.toFranchise(franchiseRequest);
        return franchiseRepository.save(franchise)
                .map(mapper::toFranchiseRequest);
    }

    public Mono<FranchiseRequest> addBranchToFranchise(String franchiseId, BranchRequest branchRequest) {
        return franchiseRepository.findById(franchiseId)
                .flatMap(franchise -> {
                    franchise.getBranches().add(mapper.toBranch(branchRequest));
                    return franchiseRepository.save(franchise);
                })
                .map(mapper::toFranchiseRequest);
    }

    public Mono<FranchiseRequest> addProductToBranch(String franchiseId, String branchName, ProductRequest productRequest) {
        return franchiseRepository.findById(franchiseId)
                .flatMap(franchise -> {
                    franchise.getBranches().stream()
                            .filter(branch -> branch.getName().equals(branchName))
                            .findFirst()
                            .ifPresent(branch -> branch.getProducts().add(mapper.toProduct(productRequest)));
                    return franchiseRepository.save(franchise);
                })
                .map(mapper::toFranchiseRequest);
    }

    public Mono<FranchiseRequest> updateProductStock(String franchiseId, String branchName, String productName, int stock) {
        return franchiseRepository.findById(franchiseId)
                .flatMap(franchise -> {
                    franchise.getBranches().stream()
                            .filter(branch -> branch.getName().equals(branchName))
                            .flatMap(branch -> branch.getProducts().stream())
                            .filter(product -> product.getName().equals(productName))
                            .findFirst()
                            .ifPresent(product -> product.setStock(stock));
                    return franchiseRepository.save(franchise);
                })
                .map(mapper::toFranchiseRequest);
    }
}
