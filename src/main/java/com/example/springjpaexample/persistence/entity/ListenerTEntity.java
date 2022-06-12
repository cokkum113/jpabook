package com.example.springjpaexample.persistence.entity;

import com.example.springjpaexample.CustomListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "listener_t")
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class, CustomListener.class})
public class ListenerTEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

//    @PrePersist
//    public void prePersist() {
//        createAt = Instant.now();
//        updatedAt = Instant.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedAt = Instant.now();
//    }




}
