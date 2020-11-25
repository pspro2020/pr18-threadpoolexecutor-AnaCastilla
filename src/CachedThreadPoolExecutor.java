import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolExecutor {

    private final ThreadPoolExecutor cachedThreadPool =
            (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    void execute(Potencia potencia) {
        try {
            cachedThreadPool.execute(potencia);
            System.out.printf("%s - %s - Thread pool size: %d\n", LocalTime.now().format(dtf), Thread.currentThread().getName(), cachedThreadPool.getPoolSize());
            System.out.printf("%s - %s -  Active threads count: %d\n", LocalTime.now().format(dtf), Thread.currentThread().getName(), cachedThreadPool.getActiveCount());
        } catch (Exception e) {
            System.out.printf("%s - $s - Task rejected: %s\n", LocalTime.now().format(dtf), Thread.currentThread().getName(), potencia.getNumber());
        }
    }

    void shutdown() throws InterruptedException {
        cachedThreadPool.shutdown();
        if (cachedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("%s - %s - Terminated. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), cachedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("%s - %s - Await termination timeout. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), cachedThreadPool.getCompletedTaskCount());
        }
    }

    @SuppressWarnings("unused")
    void shutdownNow() throws InterruptedException {
        cachedThreadPool.shutdownNow();
        if (cachedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("%s - %s - Terminated. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), cachedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("%s - %s - Await termination timeout. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), cachedThreadPool.getCompletedTaskCount());
        }
    }
}
