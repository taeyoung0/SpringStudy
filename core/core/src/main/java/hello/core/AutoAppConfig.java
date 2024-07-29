package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(     //지정하지 않으면 @ComponentScan이 붙은 클래스의 패키지가 시작 위치가 됨
                    //설정 정보 클래스의 위치를 프로젝트 최상단으로 하고 basePackages를 생략
        //basePackages = "hello.core.member",
        //basePackageClasses = AutoAppConfig.class, //이 클래스가 속한 패키지부터 찾음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

//컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에 기존 예제 코드를 최대한 유지하기 위해 설정해줌
//@Component가 붙은 클래스를 스프링 빈으로 등록
public class AutoAppConfig {

//    @Bean(name= "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();        //수동 빈 등록이 우선권을 가짐(수동 빈이 자동 빈을 오버라이딩 해버린다)
//                                                    //
//    }
}
