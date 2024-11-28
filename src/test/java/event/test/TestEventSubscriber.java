package event.test;

import event.EventSubscriber;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class TestEventSubscriber<E> implements EventSubscriber<E> {
    private Consumer<E> handler;
    private Executor executor;
    private short priority = Short.MIN_VALUE;

    @Override
    public EventSubscriber<E> handle(Consumer<E> handler) {
        return handle(handler, Runnable::run);
    }

    @Override
    public EventSubscriber<E> handle(Consumer<E> handler, Executor executor) {
        this.handler = handler;
        this.executor = executor;
        return this;
    }

    @Override
    public EventSubscriber<E> priority(short priority) {
        this.priority = priority;
        return this;
    }

    CompletableFuture<E> handle(E event) {
        return CompletableFuture.supplyAsync(() -> {
            handler.accept(event);
            return event;
        }, executor);
    }

    short priority() {
        return priority;
    }
}
