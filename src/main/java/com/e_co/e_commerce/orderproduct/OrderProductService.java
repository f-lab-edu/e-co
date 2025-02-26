package com.e_co.e_commerce.orderproduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    public List<OrderProduct> saveAll(List<OrderProduct> orderProducts) {
        return orderProductRepository.saveAll(orderProducts);
    }
}
