package event;

public interface EventHandler {
    <E> EventSubscriber<E> subscribe(Class<E> event);

    <E> boolean unsubscribe(EventSubscriber<E> subscriber);
}
