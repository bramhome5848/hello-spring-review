package hello.hellospringreview.controller;

import hello.hellospringreview.domain.Member;
import hello.hellospringreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 스프링이 연관된 객체를 스프링 컨테이너에 찾아서 넣어준다 : DI - Dependency Injection
     * 컴포넌트 스캔과 자동 의존관계 설정, 자바코드로 직접 스프링 빈 등록 방식으로 DI 가능
     * 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
     * 생성자가 1개만 있으면 @Autowired 생략 가능

     * 참고1
       - 컴포넌트 스캔의 범위 : @ComponentScan 이 명시된 클래스가 위치한 패키지를 포함해서 그 하위 패키지
       - @SpringBootApplication 이 @ComponentScan 을 포함하고 있음

     * 참고2
       - DI 에는 필드주입, setter 주입, 생성자 주입이 가능하고 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장
       - 주로 정형화된 컴포넌트 스캔을 사용하지만 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) { // thymeleaf tag name 이 key 로 매핑(id가 아님)
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
