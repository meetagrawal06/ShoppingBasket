import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shopping.basket.dto.Product;
import org.shopping.basket.exceptions.ProductNotFoundException;
import org.shopping.basket.service.Basket;
import org.shopping.basket.service.ProductService;
import org.shopping.basket.service.impl.BasketImpl;
import org.shopping.basket.service.impl.ProductServiceImpl;
import org.shopping.basket.type.ProductType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasketTest {

    private ProductService productService;

    private Basket basket;

    @BeforeEach
    public void setup() {
        List<Product> products = new ArrayList<>();
        products.add((new Product(ProductType.APPLE, .35d)));
        products.add((new Product(ProductType.BANANA, .20d)));
        products.add((new Product(ProductType.MELON, .50d)));
        products.add((new Product(ProductType.LIME, .15d)));
        productService = new ProductServiceImpl(Collections.unmodifiableList(products));
        basket = new BasketImpl(productService);
    }

    @Test
    public void testEmptyBasket() {
        Assertions.assertEquals(0d, basket.getTotalBasketPrice(), "Test Failed :: testEmptyBasket");
    }

    @Test
    public void testSingleApple() {
        basket.addProduct("Apple");
        Assertions.assertEquals(0.35d, basket.getTotalBasketPrice(), "Test Failed :: testSingleApple");
    }

    @Test
    public void testSingleBanana() {
        basket.addProduct("Banana");
        Assertions.assertEquals(0.20d, basket.getTotalBasketPrice(), "Test Failed :: testSingleBanana");
    }

    @Test
    public void testSingleMelon() {
        basket.addProduct("Melon");
        Assertions.assertEquals(0.50d, basket.getTotalBasketPrice(), "Test Failed :: testSingleMelon");
    }

    @Test
    public void testSingleLime() {
        basket.addProduct("Lime");
        Assertions.assertEquals(0.15d, basket.getTotalBasketPrice(), "Test Failed :: testSingleLime");
    }

    @Test
    public void testMultipleItemBasket1() {
        basket.addProduct("Apple");
        basket.addProduct("Apple");
        basket.addProduct("Banana");
        Assertions.assertEquals(0.90d, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
    }

    @Test
    public void testMultipleItemBasket2() {
        basket.addProduct("Apple");
        basket.addProduct("Banana");
        basket.addProduct("Melon");
        basket.addProduct("Lime");
        Assertions.assertEquals(1.20d, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket2");
    }

    @Test
    public void testMultipleItemBasket3() {
        basket.addProduct("Apple");
        basket.addProduct("Banana");
        basket.addProduct("Melon");
        basket.addProduct("Lime");
        Assertions.assertEquals(1.20d, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket3");
        basket.addProduct("Apple");
        basket.addProduct("Banana");
        basket.addProduct("Melon");
        basket.addProduct("Lime");
        basket.addProduct("Apple");
        basket.addProduct("Banana");
        basket.addProduct("Melon");
        basket.addProduct("Lime");
        Assertions.assertEquals(2.95d, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket3");
    }

    @Test
    public void testDiscountedProductMelon() {
        basket.addProduct("Melon");
        Assertions.assertEquals(0.5, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Melon");
        Assertions.assertEquals(0.5, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Melon");
        Assertions.assertEquals(1.0, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Melon");
        Assertions.assertEquals(1.0d, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
    }

    @Test
    public void testDiscountedProductLime() {
        basket.addProduct("Lime");
        Assertions.assertEquals(0.15, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Lime");
        Assertions.assertEquals(0.30, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Lime");
        Assertions.assertEquals(0.30, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Lime");
        Assertions.assertEquals(0.45, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Lime");
        Assertions.assertEquals(0.60, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
        basket.addProduct("Lime");
        Assertions.assertEquals(0.60, basket.getTotalBasketPrice(), "Test Failed :: testMultipleItemBasket1");
    }

    @Test
    public void testMultipleItemWithSameTypeBasket() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> basket.addProduct("Orange"));
    }
}
