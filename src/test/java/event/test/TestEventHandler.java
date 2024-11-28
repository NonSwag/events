package event.test;

import event.Event;
import event.EventHandler;
import event.EventSubscriber;

public class TestEventHandler implements EventHandler {
    private final ServiceProvider provider;

    TestEventHandler(ServiceProvider provider) {
        this.provider = provider;
    }

    @Override
    public <E extends Event> TestEventSubscriber<E> subscribe(Class<E> event) {
        var subscriber = new TestEventSubscriber<E>();
        provider.registry().register(event, subscriber);
        return subscriber;
    }

    @Override
    public <E extends Event> boolean unsubscribe(EventSubscriber<E> subscriber) {
        return provider.registry().unregister(subscriber);
    }
}
