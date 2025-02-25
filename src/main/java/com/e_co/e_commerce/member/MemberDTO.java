package com.e_co.e_commerce.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {
    private long id;
    private String name;
    private String email;

    public Member toEntity() {
        return Member.builder().id(id).name(name).email(email).build();
    }
}
