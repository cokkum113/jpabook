package com.example.springjpaexample.persistence.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "brand")
public class Brand extends DefaultEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "corp_name")
    private String corpName;
    @Column(name = "tel")
    private String tel;
    @Column(name = "address")
    private String address;
}
