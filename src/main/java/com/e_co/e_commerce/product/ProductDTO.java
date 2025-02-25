package com.e_co.e_commerce.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private int price;
    private String description;
    private int stock;

    public Product toEntity() {
        return Product.builder().id(id).name(name).price(price).description(description).stock(stock).build();
    }

    public static ProductDTO fromEntity(Product product) {
        return ProductDTO.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).description(product.getDescription()).stock(product.getStock()).build();
    }
}
