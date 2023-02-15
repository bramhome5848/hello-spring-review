package hello.hellospringreview;

import hello.hellospringreview.repository.MemberRepository;
import hello.hellospringreview.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    private final MemberRepository memberRepository;

    // Datasource 는 데이터베이스 커넨션을 획득할 때 사용하는 개체
    // 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource 를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI 가 가능
    // 스프링 데이터 JPA 가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다. 그래서 이 또한 DI 가 가능
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
