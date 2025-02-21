package com.e_co.e_commerce.order;

import com.e_co.e_commerce.member.Member;
import com.e_co.e_commerce.member.MemberDTO;
import com.e_co.e_commerce.member.MemberRepository;
import com.e_co.e_commerce.orderproduct.OrderProduct;
import com.e_co.e_commerce.orderproduct.OrderProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    // TODO: 스프링 시큐리티를 통해 인증 구현하여 인증 통해 유저 받기
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> postOrder(@RequestBody List<OrderProduct> orderProducts) {
        // TODO: 스프링 시큐리티를 통해 인증 구현하여 인증 통해 유저 받기
        Member member = memberRepository.findById(1L).get();
        Order order = orderService.addOrder(member, orderProducts);

        // JPA 양방향 엔티티에 따른 JSON 변환시 무한 순환 참조 발생하여 무한참조 요소를 제거한 DTO로 변환
        OrderDTO orderDTO = OrderDTO.builder()
                .id(order.getId())
                .member(MemberDTO.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .email(member.getEmail())
                        .build())
                .products(order.getProducts().stream()
                        .map((orderProduct -> OrderProductDTO.builder()
                                .id(orderProduct.getId())
                                .stock(orderProduct.getStock())
                                .product(orderProduct.getProduct())
                                .build()))
                        .toList())
                .orderDate(order.getOrderDate())
                .build();

        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
