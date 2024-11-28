package event;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface EventFactory {
    <E> CompletableFuture<E> notify(E event);

    <E> CompletableFuture<E> notify(E event, Executor executor);

    <E> CompletableFuture<E> push(E event);

    <E> CompletableFuture<E> push(E event, Executor executor);
}
