package org.example.franchises.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.franchises.domain.model.Branch;
import org.example.franchises.domain.model.Product;
import org.example.franchises.infrastructure.repository.BranchRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Mono<Branch> removeProductFromBranch(String franchiseId, String branchId, String productId) {
        return branchRepository.findByFranchiseIdAndBranchId(franchiseId, branchId)
                .flatMap(branch -> {
                    branch.getProducts().removeIf(product -> product.getId().equals(productId));
                    return branchRepository.save(branch);
                });
    }

    public Mono<Map<String, List<Product>>> getProductsWithHighestStockByBranch(String franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId)
                .collectList()
                .map(branches -> branches.stream()
                        .collect(Collectors.toMap(
                                Branch::getName,
                                branch -> branch.getProducts().stream()
                                        .sorted(Comparator.comparingInt(Product::getStock).reversed())
                                        .limit(1)
                                        .collect(Collectors.toList())
                        ))
                );
    }
}
