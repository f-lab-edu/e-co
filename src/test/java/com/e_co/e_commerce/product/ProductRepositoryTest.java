package com.e_co.e_commerce.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired ProductRepository productRepository;
    List<Product> products;

    @BeforeEach
    public void setUp(){
        products = new ArrayList<>();
        products.add(new Product("TestProduct",15000,"",100,1));
        products.add(new Product("TestProduct2",15000,"",100,1));
        products.add(new Product("TestProduct3",15000,"",100,1));

    }

    @Test
    public void save() {
        Assertions.assertEquals(0, productRepository.count());
        productRepository.saveAll(products);
        Assertions.assertEquals(products.size(), productRepository.count());
        for(Product product : products){
            Assertions.assertEquals(product,productRepository.findByName(product.getName()).orElseThrow(()->new RuntimeException()));
        }
    }
}
