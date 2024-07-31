package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.*;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor            // final이 붙은 필드를 모아서 생성자를 자동으로 만듦 (ctrl+f12로 확인 가능)
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private  final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 역할과 구현을 충실히 분리함
    // 다형성도 활용했고 인터페이스와 구현 객체를 분리함
    // OCP,DIP 같은 객체지향 설계 원칙은 지킨 것 처럼 보이지만 그렇지 못함
    // discountPolicy 추상 클래스, 구현 클래스 두 클래스에 의존하고 있음, DIP 위반
    // 기능을 확장, 변경하면서 클라이언트 코드(OrderServiceImpl)를 수정해야 한다 OCP 위반


    // 인터페이스에만 의존하도록 코드 설계
    // 별도의 구성 클래스를 만들어 준다
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);        // 회원 ID로 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // SRP(단일 책임 원칙)설계, 할인 정책을 적용하여 할인 금액 계산

        return new Order(memberId, itemName, itemPrice, discountPrice); // 새로운 주문 객체 생성 및 반환
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
