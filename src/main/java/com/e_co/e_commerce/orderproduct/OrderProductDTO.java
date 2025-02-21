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
}
