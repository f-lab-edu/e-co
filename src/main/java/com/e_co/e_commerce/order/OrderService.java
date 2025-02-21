package com.e_co.e_commerce.order;

import com.e_co.e_commerce.member.Member;
import com.e_co.e_commerce.orderproduct.OrderProduct;
import com.e_co.e_commerce.orderproduct.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;

    @Transactional
    public Order addOrder(Member member, List<OrderProduct> orderProducts) {
        Order order = Order.builder()
                .member(member)
                .products(new ArrayList<>())
                .orderDate(new Timestamp(new Date().getTime()))
                .build();

        Order savedOrder = orderRepository.save(order);

        // 양방향 관계 설정
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(savedOrder);
        }

        orderProductService.saveAll(orderProducts);

        return order;
    }
}
