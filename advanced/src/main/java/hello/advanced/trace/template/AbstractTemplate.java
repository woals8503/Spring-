package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    @Autowired
    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;  //예외처리때문에 바깥 존재

        try {
            status = trace.begin(message);
            //로직 호출
            T result = call();
            trace.end(status);
            return result;
        }catch(Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();

}
