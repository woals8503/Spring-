package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    public void field() {
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");
        
        //A가 완전히 끝난 후
        threadA.start();
        sleep(100); //동시성 문제 발생

        //2초 후 B 시작 ( 동시성 문제 발생 X )
        threadB.start();
        sleep(3000);    // 메인 스레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
