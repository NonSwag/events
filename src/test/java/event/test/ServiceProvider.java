package event.test;

class ServiceProvider {
    private final TestEventFactory factory = new TestEventFactory(this);
    private final TestEventHandler handler = new TestEventHandler(this);
    private final TestSubscriptionRegistry registry = new TestSubscriptionRegistry();

    TestEventFactory factory() {
        return factory;
    }

    TestEventHandler handler() {
        return handler;
    }

    TestSubscriptionRegistry registry() {
        return registry;
    }
}
