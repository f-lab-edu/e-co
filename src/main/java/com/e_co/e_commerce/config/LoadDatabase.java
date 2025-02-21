package com.e_co.e_commerce.config;

import com.e_co.e_commerce.member.Member;
import com.e_co.e_commerce.member.MemberService;
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
    CommandLineRunner initDatabase(ProductService productService, MemberService memberService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // 프로덕트 등록
                for (int i = 0; i < 25; i++) {
                    Random random = new Random();
                    log.info("Preloading {}", productService.save(Product
                            .builder()
                            .name("TestProduct" + i)
                            .price(random.nextInt(10) * 1000 + 10000)
                            .description("")
                            .stock(random.nextInt(10))
                            .sellerId(1)
                            .build()));
                }

                Member member = Member.builder()
                        .name("김멤버")
                        .email("test@eco.com")
                        .build();
                memberService.save(member);
            }
        };
    }
}
