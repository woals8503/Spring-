package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0,8);
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    // 아이디는 같고 생성될 수록 level이 증가한다.
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    // 아이디는 같고 생성될 수록 level이 감소한다.
    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    // 첫번째 레벨인지 확인
    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
