package org.shopping.basket.service;

import org.shopping.basket.dto.BasketItem;

public interface Discount {
    Double applyDiscount(BasketItem basketItem);
}
