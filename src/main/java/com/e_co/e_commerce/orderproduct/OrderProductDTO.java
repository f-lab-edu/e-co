package com.e_co.e_commerce.orderproduct;

import com.e_co.e_commerce.product.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductDTO {
    private long id;
    private Product product;
    private int stock;

    public OrderProduct toEntity() {
        return OrderProduct.builder().id(id).product(product).stock(stock).build();
    }
}
