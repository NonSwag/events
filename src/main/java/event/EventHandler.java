package event;

public interface EventHandler {
    <E extends Event> EventSubscriber<E> subscribe(Class<E> event);

    <E extends Event> boolean unsubscribe(EventSubscriber<E> subscriber);
}
