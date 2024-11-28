package event.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

public class EventTest {
    private static final ServiceProvider provider = new ServiceProvider();

    @BeforeAll
    public static void setup() {
        provider.handler().subscribe(TestEvent.class)
                .handle(event -> {
                    System.out.println("++++");
                    System.out.println(event);
                    event.setCancelled(true);
                    System.out.println(event);
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("++++");
                }, Executors.newSingleThreadExecutor());
    }

    @Test
    public void testCancelled() {
        var event = provider.factory().push(new TestEvent()).join();
        System.out.println(event);
        System.out.println(Thread.currentThread().getName());
        Assertions.assertTrue(event.isCancelled());
    }
}
