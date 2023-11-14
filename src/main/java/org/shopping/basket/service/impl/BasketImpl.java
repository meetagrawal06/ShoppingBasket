package org.shopping.basket.service.impl;

import org.shopping.basket.dto.BasketItem;
import org.shopping.basket.dto.Offer;
import org.shopping.basket.service.Basket;
import org.shopping.basket.service.ProductService;
import org.shopping.basket.type.OfferMap;
import org.shopping.basket.type.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BasketImpl implements Basket {
    private final List<BasketItem> basketItems = new ArrayList<>();

    private final ProductService productService;


    public BasketImpl(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(String productName) {
        basketItems.stream()
                .filter(basketItem -> basketItem.getProduct().getProductType().equals(productService.findProduct(productName)))
                .findFirst()
                .ifPresentOrElse(basketItem -> basketItem.addQuantity(),
                        () -> basketItems.add(new BasketItem(productService.findProduct(productName)))
                );
    }

    public Double getTotalBasketPrice() {
        Double basketPrice = basketItems.stream().map(item -> getBasketItemPrice(item)).reduce(
                Double::sum).orElseGet(() -> 0d);

        BigDecimal value = BigDecimal.valueOf(basketPrice);
        value = value.setScale(2, RoundingMode.HALF_UP);

        return value.doubleValue();
    }

    public Double getBasketItemPrice(BasketItem item) {
        Double price = item.getProduct().getUnitPrice() * item.getQuantity();
        List<Offer> offers = OfferMap.getInstance().getOffers();

        Double discount = offers.stream().filter(offer -> item.getProduct().getProductType().equals(offer.getProductType()))
                .map(offer -> offer.getDiscountType().getDiscount().applyDiscount(item)).reduce(Double::sum).orElseGet(() -> 0d);
        price = price - discount;
        BigDecimal value = BigDecimal.valueOf(price);
        value = value.setScale(2, RoundingMode.HALF_UP);
        return value.doubleValue();
    }
}
