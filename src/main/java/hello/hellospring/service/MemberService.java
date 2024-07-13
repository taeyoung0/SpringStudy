package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) { // 외부에서 의존성 주입을 통해 동일한 인스턴스 사용 가능
        this.memberRepository = memberRepository;       // 코드의 유연성과 재사용성을 높이고 클래스 간의 결합도를 낮춤
    }
    
    // 회원가입 메서드
    public Long join(Member member) {

        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);      // member 객체 저장
        return member.getId();          // member 객체의 id 반환
    }

    private void validateDuplicateMember(Member member) {       // 중복 회원 검증 메서드
        memberRepository.findByName(member.getName())           // 주어진 이름을 가지고 findByName 메서드 실행
                .ifPresent(m -> {                               // isPresent는 값이 있으면 실행되는 메서드
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회 메서드
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회 메서드
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);     // 매개변수로 받은 값을 사용하여 회원Id 조회
                                                        // 전달한 Id 값과 동일한 Optinal<Member> 객체 반환
    }
}
