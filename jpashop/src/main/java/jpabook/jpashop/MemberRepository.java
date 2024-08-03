package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext         // 엔터티 매니저를 자동으로 주입해주는 JPA 애노테이션
    private EntityManager em;


    public Long save(Member member) {
        em.persist(member);     // 주어진 엔티티 객체를 데이터베이스에 영속화(persist)하는 역할
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);       // 첫번째 인자는 검색할 엔티티의 타입을 지정
    }
}
