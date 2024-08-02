package org.example.franchises.application.port.in.controllers.models;

import lombok.Data;

import java.util.List;

@Data
public class BranchRequest {
    private String name;
    private List<ProductRequest> products;
}
