package com.example.springjpaexample.persistence;

import com.example.springjpaexample.dto.MemberOrderRequest;
import com.example.springjpaexample.persistence.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByAddressContain(String address);

    List<Member> findByRequest(MemberOrderRequest request);

    List<Member> findByOrderedWithBrandName(String brandNameContains);
}
