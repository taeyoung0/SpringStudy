package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component  //memoryMemberRepository 이름으로 스프링 빈에 등록
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member > store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);  // member의 id를 키로, member 객체를 값으로 저장

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // memberId를 키로 하여 저장소에서 값을 가져옴
    }
}
