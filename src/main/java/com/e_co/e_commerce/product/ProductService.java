package com.e_co.e_commerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    long getCount();

    Product save(Product product);

    Product getProduct(long id);

    /**
     * 페이지네이션, 필터, 정렬이 적용된 여러 상품 가져오기
     */
    Page<Product> getProducts(String name, String description, boolean isOnlyExist, Pageable pageable);
}
