package org.example.franchises.application.port.in.controllers;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.franchises.domain.model.Product;
import org.example.franchises.domain.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/franchises/{franchiseId}/branches")
@RequiredArgsConstructor
public class BranchController {

    private BranchService branchService;

    @DeleteMapping("/{branchId}/products/{productId}")
    public Mono<ResponseEntity<Void>> removeProductFromBranch(
            @PathVariable String franchiseId,
            @PathVariable String branchId,
            @PathVariable String productId) {
        return branchService.removeProductFromBranch(franchiseId, branchId, productId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/highest-stock")
    public Mono<ResponseEntity<Map<String, List<Product>>>> getProductsWithHighestStockByBranch(
            @PathVariable String franchiseId) {
        return branchService.getProductsWithHighestStockByBranch(franchiseId)
                .map(ResponseEntity::ok);
    }
}
