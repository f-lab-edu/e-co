package com.e_co.e_commerce.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
public class ProductServiceTest {
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    List<Product> products;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();

        ProductService productServiceImpl = new ProductService(productRepository);
        productService = productServiceImpl;
        products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            products.add(Product
                    .builder()
                    .name("TestProduct" + i)
                    .price(15000)
                    .description("TestDescription")
                    .stock(100)
                    .sellerId(1)
                    .build());
        }
    }

    @Test
    public void save() {
        assertEquals(0, productService.getCount());
        List<Product> newProducts = new ArrayList<>();

        for (Product product : products) {
            Product newProduct = productService.save(product);
            newProducts.add(newProduct);
        }

        assertEquals(3, productService.getCount());

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Product newProduct = productService.getProduct(newProducts.get(i).getId());
            assertEquals(product, newProduct);
        }
    }

    @Test
    public void getProduct() {
        assertThrows(NoSuchElementException.class, () -> productService.getProduct(999999));

        Product product = Product
                .builder()
                .name("TestProduct")
                .price(15000)
                .description("TestDescription")
                .stock(100)
                .sellerId(1)
                .build();
        Product newProduct = productService.save(product);

        assertEquals(product, productService.getProduct(newProduct.getId()));
    }

    @Test
    public void getProducts() {

    }
}
