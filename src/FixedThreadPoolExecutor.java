import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExecutor {

    private final ThreadPoolExecutor fixedThreadPool =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    void execute(Potencia potencia) {
        try {
            fixedThreadPool.execute(potencia);
            System.out.printf("%s - %s - Thread pool size: %d\n", LocalTime.now().format(dtf), Thread.currentThread().getName(), fixedThreadPool.getPoolSize());
            System.out.printf("%s - %s -  Active threads count: %d\n", LocalTime.now().format(dtf), Thread.currentThread().getName(), fixedThreadPool.getActiveCount());
        } catch (Exception e) {
            System.out.printf("%s - $s - Task rejected: %s\n", LocalTime.now().format(dtf), Thread.currentThread().getName(), potencia.getNumber());
        }
    }

    void shutdown() throws InterruptedException {
        fixedThreadPool.shutdown();
        if (fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("%s - %s - Terminated. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), fixedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("%s - %s - Await termination timeout. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), fixedThreadPool.getCompletedTaskCount());
        }
    }

    @SuppressWarnings("unused")
    void shutdownNow() throws InterruptedException {
        fixedThreadPool.shutdownNow();
        if (fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("%s - %s - Terminated. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), fixedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("%s - %s - Await termination timeout. Completed: %d\n",
                    LocalTime.now().format(dtf), Thread.currentThread().getName(), fixedThreadPool.getCompletedTaskCount());
        }
    }
}
