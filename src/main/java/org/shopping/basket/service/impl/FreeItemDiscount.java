package org.shopping.basket.service.impl;

import org.shopping.basket.dto.BasketItem;
import org.shopping.basket.service.Discount;

public class FreeItemDiscount implements Discount {

    private final Integer freeItemPurchaseQuantity;

    public FreeItemDiscount(Integer freeItemPurchaseQuantity) {
        this.freeItemPurchaseQuantity = freeItemPurchaseQuantity;
    }

    @Override
    public Double applyDiscount(BasketItem basketItem) {

        int totalItemsFree = basketItem.getQuantity() / (freeItemPurchaseQuantity + 1);
        return totalItemsFree * basketItem.getProduct().getUnitPrice();
    }
}
