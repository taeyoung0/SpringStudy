package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;     // 스프링 데이터 JPA를 사용하면 @Autowired로 EntityManager 도 주입 가능


    public void save(Member member) {       // 이 메서드를 통해 insert 쿼리문을 날림
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)   // JPQL은 엔티티 객체를 대상으로 쿼리를 날림
                .getResultList(); // 엔티티의 모든 데이터를 가져오는 쿼리를 실행하고 결과를 리스트로 반환
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

}
