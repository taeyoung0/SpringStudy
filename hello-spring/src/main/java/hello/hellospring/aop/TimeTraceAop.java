package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect     // Aspect는 AOP에서 부가 기능을 정의하는 모듈
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")    // hello.hellospring 패키지와 그 하위 패키지의 모든 메서드를 대상으로 AOP를 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {     // AOP Advice 메서드, 메서드 실행 전후로 로직을 실행
        long start= System.currentTimeMillis();     // 메서드 실행 시작 시간 기록
        System.out.println("START: " + joinPoint.toString());       // 메서드 실행 시작 로그 출력

        try{
            return joinPoint.proceed();         // 대상 메서드 실행
        } finally {
            long finish = System.currentTimeMillis();       // 메서드 실행 종료 시간 기록
            long timeMs = finish -start;                    // 실행 시간 계산
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");        // 메서드 실행 종료 로그 출력
        }
    }
}
