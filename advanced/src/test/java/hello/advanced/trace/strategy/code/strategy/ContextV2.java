package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

//전략을 파라미터로 전달 받는 방식
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call();    //구현체의 오버라이딩한 메소드 호출
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("완료시간={}", resultTime);
    }
}
