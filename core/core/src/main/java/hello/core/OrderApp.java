package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;             // member 객체 id 설정
        Member member= new Member(memberId, "memberA", Grade.VIP);      // member 객체 생성
        memberService.join(member);         // member 객체를 매개변수로 회원가입 메서드 호출

        Order order = orderService.createOrder(memberId, "itemA", 10000);   // 주문 객체 생성

        System.out.println("order = " + order);
    }
}
