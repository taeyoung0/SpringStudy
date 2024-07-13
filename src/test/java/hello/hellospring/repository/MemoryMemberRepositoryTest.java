package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest { // 테스트 클래스를 정의
    MemoryMemberRepository repository = new MemoryMemberRepository(); // MemoryMemberRepository 인스턴스를 생성

    @AfterEach
    public void afterEach() { // 각 테스트 후에 실행될 메서드를 정의
        repository.clearStore(); // 저장소를 초기화하는 메서드 호출
    }

    @Test
    public void save() { // 저장 기능을 테스트하는 메서드
        Member member = new Member(); // 새로운 Member 객체를 생성
        member.setName("spring"); // member 객체에 이름을 설정

        repository.save(member); // repository에 member 객체를 저장

        Member result = repository.findById(member.getId()).get(); // 저장된 member 객체를 ID로 조회
        assertThat(member).isEqualTo(result); // 조회한 객체가 저장한 객체와 동일한지 검증
    }

    @Test
    public void findByName() { // 이름으로 조회 기능을 테스트하는 메서드
        Member member1 = new Member(); // 첫 번째 Member 객체 생성
        member1.setName("spring1"); // member1 객체에 이름 설정
        repository.save(member1); // member1 객체를 repository에 저장

        Member member2 = new Member(); // 두 번째 Member 객체 생성
        member2.setName("spring2"); // member2 객체에 이름 설정
        repository.save(member2); // member2 객체를 repository에 저장

        Member result = repository.findByName("spring1").get(); // 이름으로 member1 객체 조회

        assertThat(result).isEqualTo(member1); // 조회한 객체가 member1과 동일한지 검증
    }

    @Test
    public void findAll() { // 모든 회원 조회 기능을 테스트하는 메서드
        Member member1 = new Member(); // 첫 번째 Member 객체 생성
        member1.setName("spring1"); // member1 객체에 이름 설정
        repository.save(member1); // member1 객체를 repository에 저장

        Member member2 = new Member(); // 두 번째 Member 객체 생성
        member2.setName("spring2"); // member2 객체에 이름 설정
        repository.save(member2); // member2 객체를 repository에 저장

        List<Member> result = repository.findAll(); // 모든 회원을 조회

        assertThat(result.size()).isEqualTo(2); // 조회한 회원의 수가 2명인지 검증
    }
}
