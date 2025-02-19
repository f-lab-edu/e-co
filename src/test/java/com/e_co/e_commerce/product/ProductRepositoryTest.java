package com.e_co.e_commerce.product;

import net.bytebuddy.utility.RandomString;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    List<Product> products;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();

        products = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Random random = new Random();
            products.add(new Product("TestProduct" + i, random.nextInt(10) * 1000 + 10000, RandomString.make(), random.nextInt(10), 1));
        }
    }

    @Test
    public void save() {
        assertEquals(0, productRepository.count());

        ArrayList<Product> savedProducts = new ArrayList<>();
        for (Product product : products) {
            savedProducts.add(productRepository.save(product));
        }

        assertEquals(products.size(), productRepository.count());

        for (Product product : savedProducts) {
            assertEquals(product, productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException()));
        }
    }

    @Test
    public void findAll() {
        assertEquals(0, productRepository.count());
        productRepository.saveAll(products);

        List<Product> foundProducts = productRepository.findAll();
        assertEquals(products.size(), foundProducts.size());

        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i), foundProducts.get(i));
        }
    }

    // 페이지네이션 테스트
    @Test
    public void findAllWithPagination() {
        String filterName = "";
        String filterDescription = "";

        int pageSize = 10;
        int pageTotal = (int) (Math.ceil((double) products.size() / pageSize));

        productRepository.saveAll(products);

        for (int pageNumber = 0; pageNumber < pageTotal; pageNumber++) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("price").descending());

            Page<Product> page = productRepository.findAllByNameOrDescriptionOrStockGreaterThanEqual(filterName, filterDescription, 0, pageable);
            assertEquals(pageSize, page.getSize());
            assertEquals(pageNumber, page.getNumber());
            assertEquals(pageTotal, page.getTotalPages());
        }
    }
}
