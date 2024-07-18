package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;             // member 객체 id 설정
        Member member= new Member(memberId, "memberA", Grade.VIP);      // member 객체 생성
        memberService.join(member);         // member 객체를 매개변수로 회원가입 메서드 호출

        Order order = orderService.createOrder(memberId, "itemA", 10000);   // 주문 객체 생성
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);    // 1000원 할인 되는지 확인

    }

}
