package com.onepo.server.repository;

import com.onepo.server.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //회원 저장
    public void save(Member member) {
        em.persist(member);
    }

    //회원 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //전체 회원 조회회
    public List<Member> findAll() {
        return em.createQuery("select m from Member As m", Member.class)
                .getResultList();
    }

    //중복 회원 검사를 위한 userId로 등록된 회원찾기
    public List<Member> findByUserId(String userId) {
        return em.createQuery("select m from Member As m where m.userId = :userId", Member.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
