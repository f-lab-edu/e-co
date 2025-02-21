package com.e_co.e_commerce.order;

import com.e_co.e_commerce.member.MemberDTO;
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
}
