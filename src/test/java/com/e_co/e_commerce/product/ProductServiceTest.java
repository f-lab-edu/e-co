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

        ProductService productServiceImpl = new ProductService();
        productServiceImpl.setProductRepository(productRepository);
        productService = productServiceImpl;
        products = new ArrayList<>();
        products.add(new Product("TestProduct", 15000, "", 100, 1));
        products.add(new Product("TestProduct2", 18000, "", 2100, 1));
        products.add(new Product("TestProduct3", 20000, "", 1100, 1));
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

        Product product = new Product("TestProduct", 15000, "", 100, 1);
        Product newProduct = productService.save(product);

        assertEquals(product, productService.getProduct(newProduct.getId()));
    }

    @Test
    public void getProducts() {

    }
}
