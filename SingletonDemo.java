public class SingletonDemo {
    public static void run() throws InterruptedException {
        String path = "config.txt";

        ConfigurationManager cm = ConfigurationManager.getInstance();
        cm.loadFromFile(path);

        // Multithread: all threads should use same instance
        Runnable task = () -> {
            ConfigurationManager mgr = ConfigurationManager.getInstance();
            System.out.println(Thread.currentThread().getName() +
                    " instance hash=" + System.identityHashCode(mgr));

            mgr.set("lastThread", Thread.currentThread().getName());
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        Thread t3 = new Thread(task, "T3");

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        cm.saveToFile(path);

        System.out.println("Saved config:");
        System.out.println(cm.getAll());
    }
}
