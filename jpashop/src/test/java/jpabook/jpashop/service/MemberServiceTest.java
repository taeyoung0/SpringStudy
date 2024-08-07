package jpabook.jpashop.service;


import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("userA");

        //when
        Long saveId = memberService.join(member);

        //then
//        Assertions.assertThat(member.getId()).isEqualTo(saveId);     // 나는 id만 비교했음
        assertEquals(member, memberRepository.findOne(saveId));    // 강의에서는 저장되어있는 객체를 꺼내와서 비교함
    }

    @Test(expected = IllegalStateException.class)       // 예외가 발생하면 테스트 성공
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("userA");

        Member member2 = new Member();
        member2.setName("userA");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");       // 예외가 발생하면 fail메서드는 호출x

    }

}