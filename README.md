# events

There is no general event class or interface users have to extend because anything can be used as an event

```java
import java.util.concurrent.Executors;

// example on how to call events
public void fireTest() {
    eventFactory().fire(new TestEvent() /* omitting the executor */)
            .thenAccept(event -> {
                System.out.println(Thread.currentThread().getName()); // still on the same thread
            });
}

// example on how to listen to events
public void handleTestEvents() {
    eventHandler().subscribe(TestEvent.class)
            .handle(event -> {
                System.out.println(Thread.currentThread().getName()); // different thread
            }, Executors.newSingleThreadExecutor() /* optionally define an executor */);
}
```

It is possible but not required to define an executor for `fire`, `notify` and `subscribn`<br>
Defining an executor will perform the logic on another thread, omitting it will perform it on the same thread

A concept for registering classes as event handlers to listen for multiple events in one class is being worked on