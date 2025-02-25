package com.e_co.e_commerce.order;

import com.e_co.e_commerce.order.dto.OrderDTO;
import com.e_co.e_commerce.order.dto.OrderRequestDTO;
import com.e_co.e_commerce.orderproduct.OrderProduct;
import com.e_co.e_commerce.orderproduct.OrderProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDTO addOrder(OrderRequestDTO orderRequestDTO) {
        Order order = Order.builder()
                .member(orderRequestDTO.getMember().toEntity())
                .products(orderRequestDTO.getOrderProducts()
                        .stream()
                        .map((OrderProductDTO::toEntity))
                        .toList())
                .orderDate(new Timestamp(new Date().getTime()))
                .build();
        // 양방향 관계 설정
        for (OrderProduct orderProduct : order.getProducts()) {
            orderProduct.setOrder(order);
        }

        orderRepository.save(order);

        return OrderDTO.fromEntity(order);
    }
}
