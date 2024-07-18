package hello.core.member;

public interface MemberRepository { // DB가 선정되지 않아서 인터페이스로 구현

    void save(Member member);

    Member findById(Long memberId);
}
