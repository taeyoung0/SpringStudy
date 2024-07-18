package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import io.micrometer.observation.ObservationRegistry;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);        // 회원 ID로 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // SRP(단일 책임 원칙)설계, 할인 정책을 적용하여 할인 금액 계산

        return new Order(memberId, itemName, itemPrice, discountPrice); // 새로운 주문 객체 생성 및 반환
    }
}
