package event.test;

import event.Event;
import event.EventFactory;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TestEventFactory implements EventFactory {
    private final ServiceProvider provider;

    TestEventFactory(ServiceProvider provider) {
        this.provider = provider;
    }

    @Override
    public <E extends Event> CompletableFuture<E> notify(E event) {
        return notify(event, Runnable::run);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Event> CompletableFuture<E> notify(E event, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            provider.registry().subscriptions((Class<E>) event.getClass())
                    .sorted(Comparator.comparingInt(TestEventSubscriber::priority))
                    .forEach(subscriber -> subscriber.handle(event));
            return event;
        }, executor);
    }

    @Override
    public <E extends Event> CompletableFuture<E> push(E event) {
        return push(event, Runnable::run);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Event> CompletableFuture<E> push(E event, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            provider.registry().subscriptions((Class<E>) event.getClass())
                    .sorted(Comparator.comparingInt(TestEventSubscriber::priority))
                    .map(subscriber -> subscriber.handle(event))
                    .forEach(CompletableFuture::join);
            return event;
        }, executor);
    }
}
