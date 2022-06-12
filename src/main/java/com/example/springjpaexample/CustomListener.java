package com.example.springjpaexample;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CustomListener {
    @PrePersist
    public void a(Object o) {
        System.out.println("PERSIST");

    }
    @PreUpdate
    public void b(Object o) {
        System.out.println("UPDATE");

    }
}
