package sales.processor;

/**
 * Created by sanver.
 */
public interface MessageProcessor<T> {
    public String process(T message);
}
