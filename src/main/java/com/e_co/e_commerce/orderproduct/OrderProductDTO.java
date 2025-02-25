package com.e_co.e_commerce.orderproduct;

import com.e_co.e_commerce.product.ProductDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductDTO {
    private long id;
    private ProductDTO product;
    private int stock;

    public OrderProduct toEntity() {
        return OrderProduct.builder().id(id).product(product.toEntity()).stock(stock).build();
    }

    public static OrderProductDTO fromEntity(OrderProduct orderProduct) {
        return OrderProductDTO.builder().id(orderProduct.getId()).product(ProductDTO.fromEntity(orderProduct.getProduct())).stock(orderProduct.getStock()).build();
    }
}
