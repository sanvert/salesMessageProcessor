package sales.adapter;

/**
 * Created by sanver.
 */
public interface MessageAdapter<T> {

    public T adapt(String content);

}
