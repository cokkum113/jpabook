package com.example.springjpaexample.persistence.entity;

import com.example.springjpaexample.BooleanYNConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "convert_test")
@NoArgsConstructor
@AllArgsConstructor
@Convert(converter = BooleanYNConverter.class, attributeName = "vip")
public class ConvertEntity extends DefaultEntity{

//    @Convert(converter = BooleanYNConverter.class)
    private Boolean vip;

}
