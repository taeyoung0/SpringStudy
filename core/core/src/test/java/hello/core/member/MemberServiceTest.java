package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);   // member 객체 생성

        // when
        memberService.join(member);                 // member 객체를 매개변수로 join 메서드 호출
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);        // 객체의 속성값이 동일한지 비교
    }
}
