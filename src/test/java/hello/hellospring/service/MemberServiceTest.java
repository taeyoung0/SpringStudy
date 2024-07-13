package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach         // 테스트 메서드 실행 전에 이 메서드 실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();    // 새로운 MemoryMemberRepository 인스턴스를 생성
        memberService = new MemberService(memberRepository);    // 생성자를 통해 memberRepository를 주입받아 MemberService 인스턴스를 생성
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given 테스트에 사용할 데이터를 준비
        Member member = new Member();
        member.setName("hello");

        // when 테스트에서 하고자 하는 동작 수행
        Long saveId = memberService.join(member);       // hello 이름으로 MemberService클래스의 join 메서드 호출
                                                        // saveId에 반환된 id값 저장

        // then 결과 예상
        Member findMember = memberService.findOne(saveId).get();        // saveId에 저장된 값으로 MemberService 클래스의 findOne 메서드 호출
                                                                        // 전달한 Id 값과 동일한 Optinal<Member> 객체 반환 후 get으로 추출
        assertThat(member.getName()).isEqualTo(findMember.getName());   // 원래 member 객체의 이름과 찾아온 findMember 객체의 이름이 동일한지 검증
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // assertThrows 메서드는 특정 예외가 발생하는지 테스트, 람다 표현식으로 예외를 발생
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외 발생 메시지가 기대한 문자열과 같은지 확인


 /*       try {
            memberService.join(member2);    // member2 객체를 회원 가입 시도, 중복된 이름으로 인해 예외 발생 예상
            fail();                 // 예외가 발생하지 않으면 테스트 실패 (명시적으로 실패 처리)
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외 발생 메시지가 같은지 확인
        }*/

        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}