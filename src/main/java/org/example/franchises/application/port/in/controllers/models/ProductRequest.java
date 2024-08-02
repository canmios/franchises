package org.example.franchises.application.port.in.controllers.models;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private int stock;
}
