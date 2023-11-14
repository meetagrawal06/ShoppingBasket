package org.shopping.basket.service;

import org.shopping.basket.dto.Product;
import org.shopping.basket.type.ProductType;

public interface ProductService {
    Product findProduct(String productName);

    Double fetchProductPrice(ProductType productType) throws NullPointerException;
}
