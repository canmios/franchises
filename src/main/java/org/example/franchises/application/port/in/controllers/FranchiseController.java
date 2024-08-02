package org.example.franchises.application.port.in.controllers;

import lombok.RequiredArgsConstructor;
import org.example.franchises.application.port.in.controllers.models.BranchRequest;
import org.example.franchises.application.port.in.controllers.models.FranchiseRequest;
import org.example.franchises.application.port.in.controllers.models.ProductRequest;
import org.example.franchises.domain.service.FranchiseService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseService franchiseService;

    @PostMapping
    public Mono<FranchiseRequest> addFranchise(@RequestBody FranchiseRequest franchiseRequest) {
        return franchiseService.addFranchise(franchiseRequest);
    }

    @PostMapping("/{franchiseId}/branches")
    public Mono<FranchiseRequest> addBranchToFranchise(@PathVariable String franchiseId
            , @RequestBody BranchRequest branchRequest) {
        return franchiseService.addBranchToFranchise(franchiseId, branchRequest);
    }

    @PostMapping("/{franchiseId}/branches/{branchName}/products")
    public Mono<FranchiseRequest> addProductToBranch(@PathVariable String franchiseId
            , @PathVariable String branchName, @RequestBody ProductRequest productRequest) {
        return franchiseService.addProductToBranch(franchiseId, branchName, productRequest);
    }

    @PutMapping("/{franchiseId}/branches/{branchName}/products/{productName}/stock")
    public Mono<FranchiseRequest> updateProductStock(@PathVariable String franchiseId
            , @PathVariable String branchName, @PathVariable String productName, @RequestBody int stock) {
        return franchiseService.updateProductStock(franchiseId, branchName, productName, stock);
    }
}