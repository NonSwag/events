package event;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface EventFactory {
    // perform on the current thread
    // notify doesn't wait for all subscribers to complete and only informs them
    default <E> CompletableFuture<E> notify(E event) {
        return notify(event, Runnable::run);
    }

    // notify doesn't wait for all subscribers to complete and only informs them
    // the executor is used to handle subscribers that don't define an executor themselves
    <E> CompletableFuture<E> notify(E event, Executor executor);

    // perform on the current thread
    // fire calls all subscribers and waits for them to complete
    default <E> CompletableFuture<E> fire(E event) {
        return fire(event, Runnable::run);
    }

    // fire calls all subscribers and waits for them to complete
    // the executor is used to handle subscribers that don't define an executor themselves
    <E> CompletableFuture<E> fire(E event, Executor executor);
}
