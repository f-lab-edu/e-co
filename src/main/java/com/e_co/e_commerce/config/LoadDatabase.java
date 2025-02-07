package com.e_co.e_commerce.config;

import com.e_co.e_commerce.product.Product;
import com.e_co.e_commerce.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductService productService) {

        return args -> {
            // 프로덕트 등록
            for (int i = 0; i < 25; i++) {
                Random random = new Random();
                log.info("Preloading {}", productService.save(new Product("TestProduct" + i, random.nextInt(10) * 1000 + 10000, "", random.nextInt(10), 1)));
            }
        };
    }
}