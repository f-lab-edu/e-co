package com.e_co.e_commerce.order;

import com.e_co.e_commerce.order.dto.OrderDTO;
import com.e_co.e_commerce.order.dto.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    // TODO: 스프링 시큐리티를 통해 인증 구현하여 인증 통해 유저 받기

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(orderService.addOrder(orderRequestDTO), HttpStatus.CREATED);
    }
}
