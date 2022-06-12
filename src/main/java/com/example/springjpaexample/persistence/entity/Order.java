package com.example.springjpaexample.persistence.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "`order`")
public class Order extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private Integer amount;
}
