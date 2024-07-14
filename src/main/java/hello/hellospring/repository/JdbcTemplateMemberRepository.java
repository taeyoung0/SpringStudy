package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;


public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    // 생성자가 1개 일때는 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) { // dataSource는 데이터 연결에 필요한 설정을 캡슐화한 객체
        jdbcTemplate = new JdbcTemplate((dataSource));
        // 코드 내에서 특정 데이터베이스 연결 설정에 의존하지 않게 되어 더 유연하고 재사용 가능한 코드를 작성
        //  DataSource를 주입받아 JdbcTemplate을 초기화하는 이유는 데이터베이스 연결 설정을 유연하게 관리하고
        //  데이터베이스 작업을 간편하게 수행할 수 있도록 하기 위함
    }



    @Override
    public Member save(Member member) {
        // SimpleJdbcInsert는 DB에 데이터 삽입을 간편하게 수행할 수 있게 함
        // jdbcTemplate의 설정 및 데이터 소스를 활용할 수 있게 생성자 인수로 전달
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); // insert할 테이블 이름과 자동 생성되는 키 컬럼을 지정

        Map<String, Object> parameters = new HashMap<>();   // 데이터베이스에 삽입할 데이터를 Map에 담음
        parameters.put("name", member.getName());   // "name" 필드에 member 객체의 이름을 저장

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); // insert를 실행하고 자동 생성된 키 값을 가져옴
        member.setId(key.longValue());  // 가져온 키 값을 member 객체의 id에 설정
        return member;  // 데이터베이스에 저장된 member 객체를 반환

    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id);
        return result.stream().findAny();       // 조건을 만족하는 요소 중 하나를 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),name);
        return result.stream().findAny();       // 조건을 만족하는 요소 중 하나를 반환
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {   // RowMapper<Member>는 데이터베이스의 한 줄(Row)을 Member 객체로 변환하는 역할
        return (rs, rowNum) -> {        // 람다 표현식을 사용하여 RowMapper 인터페이스의 mapRow 메서드 구현
            Member member = new Member();
            member.setId(rs.getLong("id")); // ResultSet에서 "id" 열 값을 가져와 Member 객체의 id 속성에 설정
            member.setName(rs.getString("name"));   // ResultSet에서 "name" 열 값을 가져와 Member 객체의 name 속성에 설정
            return member;
        };
    }

}
