package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;             // member 객체 id 설정
        Member member= new Member(memberId, "memberA", Grade.VIP);      // member 객체 생성
        memberService.join(member);         // member 객체를 매개변수로 회원가입 메서드 호출

        Order order = orderService.createOrder(memberId, "itemA", 10000);   // 주문 객체 생성
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);    // 1000원 할인 되는지 확인

    }

    @Test
    void fieldInjectionTest() {
        OrderServiceImpl orderService = new OrderServiceImpl();

        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.setDiscountPolicy(new FixDiscountPolicy());

        orderService.createOrder(1L, "itemA", 10000);
    }

}
