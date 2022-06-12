package com.example.springjpaexample;

import com.example.springjpaexample.dto.MemberOrderRequest;
import com.example.springjpaexample.persistence.entity.ConvertEntity;
import com.example.springjpaexample.persistence.entity.ListenerTEntity;
import com.example.springjpaexample.persistence.entity.Member;
import com.example.springjpaexample.type.Category;
import com.example.springjpaexample.type.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringJpaExampleApplicationTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void test_exampleOne() {
        List<Member> members = memberService.findByAddressContain("usa");
        assertThat(members.stream().map(Member::getId)).contains(3L);
        assertThat(members.size()).isEqualTo(1L);
    }

    @Test
    public void test_findMemberByRequest() {
        List<Member> membersOne = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .priceLow(BigDecimal.valueOf(1000L))
                .build());

        assertThat(membersOne.stream().map(Member::getId)).containsExactly(1L, 2L, 3L);
        assertThat(membersOne.size()).isEqualTo(3);

        List<Member> membersTwo = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .priceLow(BigDecimal.valueOf(250L))
                .category(Category.FOOD)
                .build());

        assertThat(membersTwo.stream().map(Member::getId)).containsExactly(2L);
        assertThat(membersTwo.size()).isEqualTo(1);

        List<Member> membersThree = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .statuses(List.of(ProductStatus.ON_SALE))
                .nameContains("IPHONE")
                .priceLow(BigDecimal.valueOf(1501))
                .build());

        assertThat(membersThree.stream().map(Member::getId)).containsExactly(2L);
        assertThat(membersThree.size()).isEqualTo(1);

        List<Member> membersFour = memberService.findByOrderedProductInformation(MemberOrderRequest.builder()
                .category(Category.ELECTRONICS)
                .statuses(List.of(ProductStatus.ON_SALE))
                .build());

        assertThat(membersFour.stream().map(Member::getId)).containsExactly(1L, 2L, 3L, 4L);
        assertThat(membersFour.size()).isEqualTo(4);
    }


    @Test
    public void test_findMemberByBrandName() {
        List<Member> membersOne = memberService.findByProductBrand("galaxy");

        assertThat(membersOne.stream().map(Member::getId)).containsExactly(1L, 3L, 4L);
        assertThat(membersOne.size()).isEqualTo(3);

        List<Member> membersTwo = memberService.findByProductBrand("e");

        assertThat(membersTwo.stream().map(Member::getId)).containsExactly(2L, 3L, 4L);
        assertThat(membersTwo.size()).isEqualTo(3);
    }

    @Test
    public void test_exampleOne_queryAnnotation() {
        List<Member> members = memberService.findByAddressContain_ByQueryAnnotation("usa");
        assertThat(members.stream().map(Member::getId)).contains(3L);
        assertThat(members.size()).isEqualTo(1L);
    }
//
//    @Test
//    public void test_findMemberByBrandName_queryAnnotation() {
//        List<Member> membersOne = memberService.findByProductBrand_ByQueryAnnotation("galaxy");
//
//        assertThat(membersOne.stream().map(Member::getId)).containsExactly(1L, 3L, 4L);
//        assertThat(membersOne.size()).isEqualTo(3);
//
//        List<Member> membersTwo = memberService.findByProductBrand_ByQueryAnnotation("e");
//
//        assertThat(membersTwo.stream().map(Member::getId)).containsExactly(2L, 3L, 4L);
//        assertThat(membersTwo.size()).isEqualTo(3);
//    }

    @Autowired
    EntityManager em;


    @Test
    @Transactional
    @Rollback(false)
    public void test_converter() {
        em.persist(new ConvertEntity(false));
        em.persist(new ConvertEntity(true));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test_listener() {
        ListenerTEntity t = new ListenerTEntity();
        t.setValue(12);
        em.persist(t);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test_listener_update() {
        ListenerTEntity t = em.find(ListenerTEntity.class, 1L);
        t.setValue(1);
        em.persist(t);
    }

    @Test
    @Transactional
    public void withOrder_test() {
        EntityGraph g = em.getEntityGraph("Member.withOrder");//우리가 붙여놓은 이름을 가지고 힌트를 꺼내오기
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", g);
        Member member = em.find(Member.class, 2L, hints);
        //힌트를 주면 outer조인으로 멤버를 찾음
    }

    @Test
    @Transactional
    public void withAll_JPQL() {
        Member member = em.createQuery("SELECT m from Member m where m.id = 1", Member.class)
                .setHint("javax.persistence.fetchgraph",em.getEntityGraph("Member.withOrder"))
                .getSingleResult();
    }

    @Test
    @Transactional
    public void withAll_JPQL2() {
        Member member = em.createQuery("SELECT m from Member m where m.id = 1", Member.class)
                .setHint("javax.persistence.fetchgraph", em.getEntityGraph("Member.withAll"))
                .getSingleResult();
    }

    @Test
    @Transactional
    public void withOrder_create() {
        EntityGraph<Member> graph = em.createEntityGraph(Member.class);
        graph.addAttributeNodes("orders");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        Member member = em.find(Member.class, 2L, hints);
    }



}
