package com.e_co.e_commerce.order;

import com.e_co.e_commerce.orderproduct.OrderProduct;
import com.e_co.e_commerce.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Table(name = "orders")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products;
    private Timestamp orderDate;
}
