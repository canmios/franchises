package org.example.franchises.application.port.in.controllers.mappers;


import org.example.franchises.application.port.in.controllers.models.BranchRequest;
import org.example.franchises.application.port.in.controllers.models.FranchiseRequest;
import org.example.franchises.application.port.in.controllers.models.ProductRequest;
import org.example.franchises.domain.model.Branch;
import org.example.franchises.domain.model.Franchise;
import org.example.franchises.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FranchiseMapper {
    FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

    FranchiseRequest toFranchiseRequest(Franchise franchise);
    Franchise toFranchise(FranchiseRequest franchiseRequest);

    BranchRequest toBranchRequest(Branch branch);
    Branch toBranch(BranchRequest branchRequest);

    ProductRequest toProductRequest(Product product);
    Product toProduct(ProductRequest productRequest);
}
