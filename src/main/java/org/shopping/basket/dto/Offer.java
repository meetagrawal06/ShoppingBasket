package org.shopping.basket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.shopping.basket.type.DiscountType;
import org.shopping.basket.type.ProductType;

@Data
@AllArgsConstructor
public class Offer {
    private ProductType productType;
    private DiscountType discountType;
}
