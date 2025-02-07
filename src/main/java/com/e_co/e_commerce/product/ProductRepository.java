package com.e_co.e_commerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // 페이지네이션, ((이름 or 내용) and 품절여부) 조건이 기본적으로 적용된 쿼리
    // 정렬도 사용 가능
    Page<Product> findAllByNameOrDescriptionOrStockGreaterThanEqual(String name, String description, int stock, Pageable pageable);
}
