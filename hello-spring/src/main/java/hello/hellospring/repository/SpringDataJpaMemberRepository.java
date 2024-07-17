package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    // JpaRepository는 스프링 데이터 JPA가 제공하는 인터페이스이다
    // 기본적으로 자동으로 CRUD제공해준다
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
