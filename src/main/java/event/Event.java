package event;

public interface Event {
    default String getEventName() {
        return getClass().getSimpleName();
    }
}
