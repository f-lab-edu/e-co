package com.e_co.e_commerce.order.dto;

import com.e_co.e_commerce.member.Member;
import com.e_co.e_commerce.member.MemberDTO;
import com.e_co.e_commerce.order.Order;
import com.e_co.e_commerce.orderproduct.OrderProductDTO;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder
public class OrderDTO {
    private long id;
    private MemberDTO member;
    private List<OrderProductDTO> products;
    private Timestamp orderDate;

    // JPA 양방향 엔티티에 따른 JSON 변환시 무한 순환 참조 발생하여 무한참조 요소를 제거한 DTO로 변환
    public static OrderDTO fromEntity(Order order) {
        Member member = order.getMember();

        return OrderDTO.builder()
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
    }
}
