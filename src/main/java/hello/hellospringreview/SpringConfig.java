package hello.hellospringreview;

import hello.hellospringreview.repository.MemberRepository;
import hello.hellospringreview.repository.MemoryMemberRepository;
import hello.hellospringreview.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
