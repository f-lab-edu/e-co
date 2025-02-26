package com.e_co.e_commerce.orderproduct;

import com.e_co.e_commerce.order.Order;
import com.e_co.e_commerce.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    private int stock;

    // 순수한 객체를 고려한 양방향 연관관계 설정
    public void setOrder(Order order) {
        if (order == null) return;
        this.order = order;
        List<OrderProduct> orderProducts = this.getOrder().getProducts();
        if (!orderProducts.contains(this))
            orderProducts.add(this);
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", stock=" + stock +
                '}';
    }
}
