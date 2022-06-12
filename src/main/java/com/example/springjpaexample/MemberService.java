package com.example.springjpaexample;

import com.example.springjpaexample.dto.MemberOrderRequest;
import com.example.springjpaexample.persistence.MemberRepository;
import com.example.springjpaexample.persistence.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * example 1
     */
    // ADDRESS에 파라미터 글자가 포함되어 있는 Member들을 추출
    @Transactional(readOnly = true)
    public List<Member> findByAddressContain(String address) {
        return memberRepository.findByAddressContain(address);
    }

    /**
     * example 2
     */
    // Member Order Request의 각 필드는 Null일 수 있으며, Null이면 해당 조건은 사용하지 않음
    // ID ASC로 Unique하게 리턴할 것
    @Transactional(readOnly = true)
    public List<Member> findByOrderedProductInformation(MemberOrderRequest memberOrderRequest) {
        return memberRepository.findByRequest(memberOrderRequest);
    }

    /**
     * example 3
     * */
    // 파라미터의 값이 포함된 브랜드 이름을 갖는 브랜드들의 상품을 구매한 모든 Member를 추출
    // 역시 Member Id asc + unique
    @Transactional(readOnly = true)
    public List<Member> findByProductBrand(String brandNameContains) {
       return memberRepository.findByOrderedWithBrandName(brandNameContains);
    }

    @Transactional(readOnly = true)
    public List<Member> findByAddressContain_ByQueryAnnotation(String address) {
//        return memberRepository.findByAddressContainAnnotation(address);
        return memberRepository.findAllByAddressContains(address);
    }
//
//    @Transactional(readOnly = true)
//    public List<Member> findByProductBrand_ByQueryAnnotation(String brandNameContains) {
//        return memberRepository.findByOrderedWithBrandNameAnnotation(brandNameContains);
//    }
}
