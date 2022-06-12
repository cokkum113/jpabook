package com.example.springjpaexample.dto;

import com.example.springjpaexample.type.Category;
import com.example.springjpaexample.type.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrderRequest {
    private BigDecimal priceLow; // 미만의 가격
    private Category category; // 이 카테고리
    private String nameContains; // 이러한 상품 이름 포함
    private List<ProductStatus> statuses; // 상태값들 NOT NULL But can be empty
}
