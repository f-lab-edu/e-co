package com.e_co.e_commerce.orderproduct;

import com.e_co.e_commerce.order.Order;
import com.e_co.e_commerce.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    private int stock;
}
