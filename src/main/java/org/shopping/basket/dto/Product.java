package org.shopping.basket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.shopping.basket.type.ProductType;

@Data
@AllArgsConstructor
public class Product {
    private ProductType productType;
    private Double unitPrice;
}
