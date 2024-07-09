package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);     // member 객체를 저장소에 저장
    Optional<Member> findById(Long id);     // Optinal은 null 대신 빈 Optinal 객체를 반환 NullPointerException 방지, 주어진 id를 가진 member 객체를 반환
    Optional<Member> findByName(String name);   // 주어진 name을 가진 member 객체를 반환
    List<Member> findAll(); // 저장소에 저장된 모든 member 객체를 리스트 형태로 반환
}

