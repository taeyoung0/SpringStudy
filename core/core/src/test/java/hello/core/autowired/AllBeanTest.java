package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // AutoAppConfig의 @ComponentScan을 통해 @Component가 붙은 Rate나 Fix를 자동으로 스캔하여 스프링 빈으로 등록하기 때문에 꺼내서 사용 가능
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);             // member 객체 생성

        // discountService를 사용하여 "fixDiscountPolicy"로 할인 가격 계산
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);        // discountService 객체가 DiscountService 클래스의 인스턴스인지 확인
        assertThat(discountPrice).isEqualTo(1000);        // 할인된 가격이 1000인지 확인

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);


    }
    static class DiscountService {
        private Map<String, DiscountPolicy> policyMap;
        private List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            // policyMap의 키는 자동으로 설정된 빈 이름("fixDiscountPolicy","rateDiscountPolicy")가 되고 값은 해당 스프링 빈 인스턴스가 된다
            this.policyMap = policyMap;         // Map으로 모든 DiscountPolicy를 주입 받음
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);    // 넘어온 "fixDiscountPolicy"가 키가되고 FixDiscountPolicy 객체를 policyMap에서 찾음
                                                                            // 그 객체의 discount 메서드를 호출하여 할인된 가격을 계산
            return discountPolicy.discount(member, price);
        }
    }
}
