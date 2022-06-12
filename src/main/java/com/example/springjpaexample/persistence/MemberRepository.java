package com.example.springjpaexample.persistence;

import com.example.springjpaexample.persistence.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
//    @Query("select m " +
//            "from Member m " +
//            "where m.address like concat('%', :address, '%')")
//    List<Member> findByAddressContainAnnotation(@Param("address") String address);

    List<Member> findAllByAddressContains(String address);

//    @Query("select distinct m +" +
//            "from ")
//    List<Member> findByOrderedWithBrandNameAnnotation(@Param("brandName") String brandName);
}
