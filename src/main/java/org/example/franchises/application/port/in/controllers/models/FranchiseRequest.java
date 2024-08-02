package org.example.franchises.application.port.in.controllers.models;

import lombok.Data;
import java.util.List;

@Data
public class FranchiseRequest {
    private String id;
    private String name;
    private List<BranchRequest> branches;
}
