package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();   // Map은 키-값 구조, 회원 ID를 키로 member 객체를 값으로 저장
                                                                // 이렇게 하면 회원ID를 키로 하여 대응하는 member객체를 빠르게 찾을수 있음
    private static long sequence = 0L;      // 회원 ID 설정 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);           // 새로운 회원 생성시 id가 1증가
        store.put(member.getId(), member);  // 설정한 id와 객체를 Map에 저장
        return member;      // 저장된 회원 객체 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // 주어진 id로 Map에서 회원 객체를 찾음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()      // store의 모든 값을 스트림으로 변환, 스트림을 사용하면 더 직관적
                .filter(member -> member.getName().equals(name)) // 저장되있는 member 객체들 중 요청한 name과 같은 member 객체를 찾음
                .findAny();     // 조건을 만족하는 요소 중 하나를 반환, 없으면 빈 Optional 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // 모든 회원 객체를 리스트로 반환
    }

    public void clearStore() {
        store.clear();
    }

}
