package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository  implements MemberRepository{

    private final EntityManager em;         // EntityManager는 JPA라이브러리를 가져오면 자동으로 생성,JPA에서 DB와 상호작용을 관리하는 핵심 객체

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);     // EntityManager를 사용하여 Member 객체를 데이터베이스에 저장
                                // persist 메서드를 사용하면 영속성 컨텍스트에 저장
                                // 영속성 컨텍스트는 엔티티 객체의 변화를 감지, 자동으로 DB에 변경 사항 반영
                                // em.find(Member.class, member.getId()) 메서드를 사용할 때 DB를 다시 조회하지 않고 메모리에서 member 객체를 가져옴
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);      // ID로 Member 객체를 영속성 컨텍스트에서 찾음
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPQL쿼리를 작성하여 Member 엔티티에서 이름이 주어진 파라미터와 일치하는 객체를 찾음
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)     // 쿼리의 :name 파라미터를 메서드 인자로 전달된 name 값으로 설정
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)   // JPQL쿼리는 DB 테이블이 아니라 엔티티 객체를 대상으로 쿼리 수행
                .getResultList();                                          // 두번째 인자는 반환될 결과 타입 여기서는 Member 엔티티 클래스임
                                                                            // select 에서 컬럼이 아니라 객체를 지정
    }
}
