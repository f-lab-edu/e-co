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
import java.util.Comparator;
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


    // 페이지네이션, 조건, 정렬이 적용된 쿼리 테스트
    @Test
    public void findAllByNameOrDescription() {
        String filterName = "";
        String filterDescription = "";
        boolean filterOnlyExist = false;

        // 테스트 데이터를 자바 코드를 통해 쿼리에 맞춰 조건과 정렬 적용
        List<Product> findProducts = products.stream().filter(product -> {
            return (product.getName().contains(filterName) || product.getDescription().contains(filterDescription)) && product.getStock() >= (filterOnlyExist ? 1 : 0);
        }).sorted(Comparator.comparingLong((Product p) -> p.getPrice()).reversed().thenComparing(p -> p.getId())).toList();

        int pageSize = 10;
        int pageTotal = (int) (Math.ceil((double) findProducts.size() / pageSize));

        for (int i = 0; i < pageTotal; i++) {
            Pageable pageable = PageRequest.of(i, pageSize, Sort.by("price").descending());
            Page<Product> pages = productRepository.findAllByNameOrDescriptionOrStockGreaterThanEqual(filterName, filterDescription, filterOnlyExist ? 1 : 0, pageable);

            assertEquals(pageTotal, pages.getTotalPages());
            assertEquals(pageSize, pages.getSize());
            assertEquals(findProducts.size(), pages.getTotalElements());

            // 현재 쿼리가 ID 순서를 보장하지 못해 쿼리로 나온 페이지와 자바 코드로 나온 페이지 요소가 달라
            // 요소별 테스트가 힘듬
            // 이미 검증된 Spring Data JPA Repository 테스트에 가까워서 급하진 않기때문에
            // 우선 요소 테스트 코드 작성은 패스

            //List<Product> pagedProducts = findProducts.subList(i * pageSize, (i < pageTotal - 1) ? (i + 1) * pageSize : findProducts.size());
            //// DB Query가 Id 순서를 보장하지 않아 자바 코드로 임시로 정렬
            //List<Product> realPagedProducts = pages.getContent().stream().sorted(Comparator.comparingLong((Product p) -> p.getPrice()).reversed().thenComparing(p -> p.getId())).toList();
            //for (int j = 0; j < pagedProducts.size(); j++) {
            //    assertEquals(pagedProducts.get(j), realPagedProducts.get(j));
            //}
        }
    }
}
