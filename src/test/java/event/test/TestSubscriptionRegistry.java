package event.test;

import event.Event;
import event.EventSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TestSubscriptionRegistry {
    private final Map<Class<? extends Event>, List<TestEventSubscriber<? extends Event>>> subscriptions = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <E extends Event> Stream<TestEventSubscriber<E>> subscriptions(Class<E> event) {
        return subscriptions.getOrDefault(event, List.of()).stream()
                .map(subscriber -> (TestEventSubscriber<E>) subscriber);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean unregister(EventSubscriber<? extends Event> subscriber) {
        return subscriptions.values().removeIf(list -> list.remove(subscriber));
    }

    public void unregisterAll() {
        subscriptions.clear();
    }

    public boolean unregisterAll(Class<? extends Event> event) {
        return subscriptions.remove(event) != null;
    }

    public void register(Class<? extends Event> event, TestEventSubscriber<? extends Event> subscriber) {
        subscriptions.computeIfAbsent(event, aClass -> new ArrayList<>())
                .add(subscriber);
    }
}
