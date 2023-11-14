package org.shopping.basket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketItem {
    private Product product;
    private Integer quantity;

    public BasketItem(Product product) {
        this.product = product;
        quantity = 1;
    }

    public void addQuantity() {
        quantity++;
    }
}
