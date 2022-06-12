package com.example.springjpaexample.persistence.entity;

import com.example.springjpaexample.type.Category;
import com.example.springjpaexample.type.ProductStatus;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "product")
public class Product extends DefaultEntity {
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
