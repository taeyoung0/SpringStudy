package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {        // 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다

    //출력 메서드 기대값 순서X
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //실제 출력 메서드 값
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    //스프링 컨테이너가 어떻게든 싱글톤을 보장해줌

    @Bean
    public MemberService memberService() {      // 생성자 주입, 실제 동작에 필요한 구현 객체를 생성
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성한 객체의 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입
    }
    @Bean
    public MemberRepository memberRepository() {       // 역할에 따른 구현 클래스를 한 눈에 들어오게 함
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();    // 할인 정책을 변경해도 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면됨
    }
}
