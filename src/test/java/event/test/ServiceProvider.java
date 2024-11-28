package event.test;

class ServiceProvider {
    final TestEventFactory factory = new TestEventFactory(this);
    final TestEventHandler handler = new TestEventHandler(this);
    final TestSubscriptionRegistry registry = new TestSubscriptionRegistry();
}
