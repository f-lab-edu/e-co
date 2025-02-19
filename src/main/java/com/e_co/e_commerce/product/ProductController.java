package com.e_co.e_commerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam("id") long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "description", required = false) String description,
                                                     @RequestParam(value = "isOnlyExist", defaultValue = "true", required = false) boolean isOnlyExist,
                                                     @PageableDefault Pageable pageable
    ) {
        return new ResponseEntity<>(productService.getProducts(name, description, isOnlyExist, pageable), HttpStatus.OK);
    }
}
