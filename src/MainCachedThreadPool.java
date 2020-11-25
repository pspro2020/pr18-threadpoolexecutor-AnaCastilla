public class MainCachedThreadPool {

    public static void main(String[] args) {

        CachedThreadPoolExecutor  cachedThreadPoolExecutor = new CachedThreadPoolExecutor();
        Potencia potencia;

        for (int i = 0; i < 10; i++) {
            potencia = new Potencia(i+1);
            cachedThreadPoolExecutor.execute(potencia);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }

        try {
            cachedThreadPoolExecutor.shutdown();
        } catch (InterruptedException e) {
            return;
        }

        System.out.println("CatchedThreadPool ha usado " + cachedThreadPoolExecutor.getLargestPoolSize() + " hilos");
    }
}
