package com.e_co.e_commerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public long getCount() {
        return productRepository.count();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id에 해당하는 상품이 없습니다."));
    }

    public Page<Product> getProducts(String name, String description, boolean isOnlyExist, Pageable pageable) {
        return productRepository.findAllByNameOrDescriptionOrStockGreaterThanEqual(name, description, isOnlyExist ? 1 : 0, pageable);
    }
}
