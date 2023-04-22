package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    public void strategyV0() {
        logic1();
        logic2();
    }

    public void logic1() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("완료시간={}", resultTime);
    }

    public void logic2() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
    
    //전략패턴 사용
    @Test
    public void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        new ContextV1(new StrategyLogic2()).execute();
    }

    @Test
    public void strategyV2() {
        //ContextV1의 생성자를 생성하면 매개변수로 Strategy 인터페이스 타입이 들어갈텐데 이 인터페이스는 구현체를 가지고있다.
        //그렇기에 람다식을 활용하여 new를 생략하고 바로 메소드를 호출하게 했다.
        //위와 다른 점은 위 코드는 StrategyLogic1이라는 클래스를 직접 생성자의 매개변수로 전달하였고
        //아래의 코드는 만들었던 클래스가 아닌 생성하면서 익명 객체를 통하여 매개변수로 전달했다.
        new ContextV1(new Strategy() {
            @Override
            public void call() {
                System.out.println("원래는 이거");
            }
        });

        new ContextV1(() -> log.info("람다식 적용")).execute();

        new ContextV1(() -> System.out.println("바꿀수도 있어")).execute();
    }
}
