package com.example.springjpaexample.persistence;

import com.example.springjpaexample.dto.MemberOrderRequest;
import com.example.springjpaexample.persistence.entity.Member;
import com.example.springjpaexample.persistence.entity.QBrand;
import com.example.springjpaexample.persistence.entity.QMember;
import com.example.springjpaexample.persistence.entity.QOrder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

import static com.example.springjpaexample.persistence.entity.QBrand.brand;
import static com.example.springjpaexample.persistence.entity.QMember.member;
import static com.example.springjpaexample.persistence.entity.QOrder.order;
import static com.example.springjpaexample.persistence.entity.QProduct.product;

@Repository
public class MemberRepositoryCustomImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    public MemberRepositoryCustomImpl() {
        super(Member.class);
    }

    //스프링에서 JPQL을 직접 구현하려고 쓰는 방식
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Member> findByAddressContain(String address) {
        //JPQL을 사용하는 방법
//        return em.createQuery("select m from Member m where m.address like concat('%', :address, '%')", Member.class)
//                .setParameter("address", address)
//                .getResultList();

//        JPAQueryFactory factory = new JPAQueryFactory(em);
//        QMember member = QMember.member;
//        return factory.selectFrom(member)
//                .where(member.address.contains(address))
//                .fetch();

        return from(member)
                .where(member.address.contains(address))
                .fetch();
    }

    @Override
    public List<Member> findByRequest(MemberOrderRequest request) {
        /*
        BooleanBuilder builder = new BooleanBuilder();
        if (request.getPriceLow() != null) {
            builder.and(order.product.price.lt(request.getPriceLow()));
        }
        if (request.getCategory() != null) {
            builder.and(order.product.category.eq(request.getCategory()));
        }
        if (request.getNameContains() != null) {
            builder.and(order.product.name.contains(request.getNameContains()));
        }
        if (request.getStatuses() != null  && !request.getStatuses().isEmpty()) {
            builder.and(order.product.status.in(request.getStatuses()));
        }

        return from(member)
                .innerJoin(order).on(member.eq(order.member))
                .where(builder)
                .orderBy(member.id.asc())
                .distinct()
                .fetch();
         */
        BooleanBuilder builder = new BooleanBuilder();
        if (request.getPriceLow() != null) {
            builder.and(product.price.lt(request.getPriceLow()));
        }
        if (request.getCategory() != null) {
            builder.and(product.category.eq(request.getCategory()));
        }
        if (request.getNameContains() != null) {
            builder.and(product.name.contains(request.getNameContains()));
        }
        if (request.getStatuses() != null  && !request.getStatuses().isEmpty()) {
            builder.and(product.status.in(request.getStatuses()));
        }
        return from(member)
                .innerJoin(order).on(member.eq(order.member))
                .innerJoin(product).on(product.eq(order.product))
                .where(builder)
                .orderBy(member.id.asc())
                .distinct()
                .fetch();
    }

    @Override
    public List<Member> findByOrderedWithBrandName(String brandNameContains) {
        return from(member)
                .innerJoin(order).on(member.eq(order.member))
                .innerJoin(product).on(product.eq(order.product))
                .innerJoin(brand).on(brand.eq(product.brand))
                .where(brand.name.contains(brandNameContains))
                .orderBy(member.id.asc())
                .distinct()
                .fetch();
    }
}
