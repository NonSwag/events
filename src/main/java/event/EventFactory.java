package event;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface EventFactory {
    <E extends Event> CompletableFuture<E> notify(E event);

    <E extends Event> CompletableFuture<E> notify(E event, Executor executor);

    <E extends Event> CompletableFuture<E> push(E event);

    <E extends Event> CompletableFuture<E> push(E event, Executor executor);
}
