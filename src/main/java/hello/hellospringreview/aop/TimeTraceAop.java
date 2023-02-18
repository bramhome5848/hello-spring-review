package hello.hellospringreview.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospringreview..*(..))")    //target
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START = " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //다음 메서드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + joinPoint + " " + timeMs + "ms");
        }
    }

    /**
     ** 시간 측정 적용의 문제 **
     * 회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아님
     * 시간을 측정하는 로직은 공통 관심 사항
     * 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어려움
     * 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어려움
     * 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 함

     ** 해결 **
     * 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리
     * 시간을 측정하는 로직을 별도의 공통 로직으로 만듦
     * 핵심 관심 사항을 깔끔하게 유지
     * 변경이 필요하면 이 로직만 변경
     * 원하는 적용 대상을 선택 가능

     * AOP 적용전 의존 관계
     * MemberController -> MemberService
     * MemberController -> MemberService -> MemberRepository

     * AOP 적용 후 의존 관계
     * MemberController -> Proxy MemberService(joinPoint.proceed()) -> MemberService
     * MemberController -> Proxy MemberService(joinPoint.proceed())
      -> MemberService -> Proxy MemberRepository(joinPoint.proceed()) -> MemberRepository
     */
}
