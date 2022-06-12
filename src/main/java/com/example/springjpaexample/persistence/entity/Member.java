package com.example.springjpaexample.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
//~를 가져와라 라는 힌트를 만들것이다
@NamedEntityGraph(name = "Member.withOrder", attributeNodes = {@NamedAttributeNode("orders")})
@NamedEntityGraph(name = "Member.withAll", attributeNodes = {@NamedAttributeNode(value = "orders", subgraph = "orders")},
subgraphs = @NamedSubgraph(name = "orders", attributeNodes = {@NamedAttributeNode("product")}))
public class Member extends DefaultEntity {
    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
