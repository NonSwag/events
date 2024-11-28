package event.test;

import event.Cancellable;
import event.Event;

public class TestEvent implements Event, Cancellable {
    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String getEventName() {
        return "test";
    }

    @Override
    public String toString() {
        return "TestEvent{" +
               "cancelled=" + cancelled +
               '}';
    }
}
