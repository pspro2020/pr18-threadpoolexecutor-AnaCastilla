public class MainFixedThreadPool {

    public static void main(String[] args) {

        FixedThreadPoolExecutor fixedThreadPoolExecutor = new FixedThreadPoolExecutor();
        Potencia potencia;

        for (int i = 0; i < 10; i++) {
            potencia = new Potencia(i+1);
            fixedThreadPoolExecutor.execute(potencia);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }

        try {
            fixedThreadPoolExecutor.shutdown();
        } catch (InterruptedException e) {
            return;
        }

        System.out.println("FixedThreadPool ha usado " + fixedThreadPoolExecutor.getLargestPoolSize() + " hilos");
    }
}
