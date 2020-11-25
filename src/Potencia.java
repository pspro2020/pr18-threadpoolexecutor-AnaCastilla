import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Potencia implements Runnable {

    private final int number;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss:ms");

    Potencia(int number) {
        this.number = number;
    }

    public int getNumber(){
        return this.getNumber();
    }

    @Override
    public void run() {
        System.out.printf("%s - %s - Started at: %s\n",
                 Thread.currentThread().getName(), number, LocalTime.now().format(dtf));
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.printf("%s: %d ^ %d = %.2f\n", Thread.currentThread().getName(),
                        number, i, Math.pow(number, i));

            }
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.printf("%s - %s - Interrupted at: %s\n",
                    Thread.currentThread().getName(), number, LocalTime.now().format(dtf));
            return;
        }
        System.out.printf("%s -> %s -> Finished at: %s\n",
                Thread.currentThread().getName(), number, LocalTime.now().format(dtf));

    }
}
