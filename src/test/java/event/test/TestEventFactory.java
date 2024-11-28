package event.test;

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
    @SuppressWarnings("unchecked")
    public <E> CompletableFuture<E> notify(E event, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            provider.registry.subscriptions((Class<E>) event.getClass())
                    .sorted(Comparator.comparingInt(TestEventSubscriber::priority))
                    .forEach(subscriber -> subscriber.handle(event));
            return event;
        }, executor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> CompletableFuture<E> fire(E event, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            provider.registry.subscriptions((Class<E>) event.getClass())
                    .sorted(Comparator.comparingInt(TestEventSubscriber::priority))
                    .map(subscriber -> subscriber.handle(event))
                    .forEach(CompletableFuture::join);
            return event;
        }, executor);
    }
}
