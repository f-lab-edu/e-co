package com.e_co.e_commerce.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long getCount() {
        return productRepository.count();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Product> getProducts(String name, String description, boolean isOnlyExist, Pageable pageable) {
        return productRepository.findAllByNameOrDescriptionOrStockGreaterThanEqual(name, description, isOnlyExist ? 1 : 0, pageable);
    }
}
