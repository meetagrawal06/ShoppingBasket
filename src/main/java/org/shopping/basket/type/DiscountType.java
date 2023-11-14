package org.shopping.basket.type;

import org.shopping.basket.service.Discount;
import org.shopping.basket.service.impl.FreeItemDiscount;

public enum DiscountType {
    BUY_ONE_GET_ONE_FREE(new FreeItemDiscount(1)),
    THREE_FOR_TWO(new FreeItemDiscount(2));
    private final Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    DiscountType(Discount discount) {
        this.discount = discount;
    }
}
