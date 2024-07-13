package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller     // 스프링 컨테이너에 자동으로 스피링 빈 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;     // memberController가 생성될때 스프링 빈에 등록 되어있는 memberService 객체를 주입
    }
}
