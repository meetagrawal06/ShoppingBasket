package org.shopping.basket.service;

public interface Basket {
    void addProduct(String productName);

    Double getTotalBasketPrice();
}
