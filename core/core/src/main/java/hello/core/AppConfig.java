package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {        // 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다

    public MemberService memberService() {      // 생성자 주입, 실제 동작에 필요한 구현 객체를 생성
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성한 객체의 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입
    }

    public OrderService orderService() {        // 생성자 주입
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
