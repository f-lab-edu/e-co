package com.e_co.e_commerce.order.dto;

import com.e_co.e_commerce.member.MemberDTO;
import com.e_co.e_commerce.orderproduct.OrderProductDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDTO {
    private MemberDTO member;
    private List<OrderProductDTO> orderProducts;
}
