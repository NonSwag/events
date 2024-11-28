package event;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface EventSubscriber<E> {
    EventSubscriber<E> handle(Consumer<E> handler);

    EventSubscriber<E> handle(Consumer<E> handler, Executor executor);

    // the higher the priority, the earlier the subscription is called
    EventSubscriber<E> priority(short priority);
}
