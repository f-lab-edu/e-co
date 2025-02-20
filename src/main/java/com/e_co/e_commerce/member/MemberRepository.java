package com.e_co.e_commerce.member;

import org.springframework.data.repository.ListCrudRepository;

public interface MemberRepository extends ListCrudRepository<Member, Long> {
}
