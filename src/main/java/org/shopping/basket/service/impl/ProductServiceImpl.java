package org.shopping.basket.service.impl;

import org.shopping.basket.dto.Product;
import org.shopping.basket.exceptions.ProductNotFoundException;
import org.shopping.basket.service.ProductService;
import org.shopping.basket.type.ProductType;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final List<Product> products;

    public ProductServiceImpl(List<Product> products) {
        this.products = products;
    }

    @Override
    public Product findProduct(String productName) {
        return products.stream().filter(product -> product.getProductType().toString().equals(productName.toUpperCase())).findAny()
                .orElseThrow(() -> new ProductNotFoundException());
    }

    @Override
    public Double fetchProductPrice(ProductType productType) throws NullPointerException {
        Optional<Product> any = products.stream().filter(product -> product.getProductType().equals(productType))
                .findAny();
        if (any.isPresent()) {
            return any.get().getUnitPrice();
        }
        throw new ProductNotFoundException();
    }
}
