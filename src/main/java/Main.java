import org.shopping.basket.dto.Product;
import org.shopping.basket.service.Basket;
import org.shopping.basket.service.ProductService;
import org.shopping.basket.service.impl.BasketImpl;
import org.shopping.basket.service.impl.ProductServiceImpl;
import org.shopping.basket.type.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello and welcome to Shopping cart amount calculator!");
            Basket basket = initializeBasket();
            Arrays.stream(args).forEach(productName ->
                    basket.addProduct(productName));

            System.out.println("Total price of basket is " + basket.getTotalBasketPrice());
        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
            throw e;
        }
    }
    public static Basket initializeBasket() {
        List<Product> products = new ArrayList<>();
        products.add((new Product(ProductType.APPLE, .35d)));
        products.add((new Product(ProductType.BANANA, .20d)));
        products.add((new Product(ProductType.MELON, .50d)));
        products.add((new Product(ProductType.LIME, .15d)));
        ProductService productService = new ProductServiceImpl(Collections.unmodifiableList(products));
        Basket basket = new BasketImpl(productService);
        return basket;
    }
}