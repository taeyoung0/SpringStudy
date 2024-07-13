package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      // 스프링 설정 파일을 의미, 스프링 빈 설정
public class SpringConfig {

    @Bean       // 스프링 빈을 자바 코드로 직접 등록
    public MemberService memberService() {      // memberService 객체 생성
        return new MemberService(memberRepository());       // memberRepository 메서드를 통해 생성된 MemoryMemberRepository 클래스를 가진
                                                            // memberRepository 객체를 memberService 생성자의 파라미터로 전달
                                                            // MemoryMemberRepository 클래스를 가진 memberService 인스턴스 생성 후 반환
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();        // 구현체가 MemoryMemberRepository이므로 이를 반환
    }
}
