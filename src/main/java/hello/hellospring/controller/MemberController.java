package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller     // 스프링 컨테이너에 자동으로 스피링 빈 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;     // memberController가 생성될때 스프링 빈에 등록 되어있는 memberService 객체를 주입
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";  // createMemberForm.html 랜더링
    }

    @PostMapping("/members/new")                // "/members/new" URL로 POST 요청이 들어오면 create 메서드와 매핑
    public String create(MemberForm form) {     // 클라이언트로부터 전달된 데이터를 MemberForm 객체로 받음
        Member member = new Member();           // member 객체 생성
        member.setName(form.getName());         // member 객체에 form에 들어온 name값으로 설정

        memberService.join(member);             // member객체를 memberService를 통해 회원 가입 메서드 실행

        return "redirect:/";                    // 회원 가입 후 루트 url로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {   // model은 컨트롤러에서 뷰로 데이터를 전달할 때 사용, 데이터는 키-값 쌍으로 저장
        List<Member> members = memberService.findMembers();  // memberService를 통해 모든 회원 목록을 조회
        model.addAttribute("members", members);  // members 리스트를 "members"라는 키로 모델에 추가
                                                             // 조회한 회원 목록을 모델에 추가하여 뷰로 전달


        return "members/memberList";    // "members/memberList.html" 뷰를 렌더링
    }
}
