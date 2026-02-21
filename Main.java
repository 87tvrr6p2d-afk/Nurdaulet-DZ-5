public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== SINGLETON TEST ===");
        SingletonDemo.run();

        System.out.println("\n=== BUILDER TEST ===");
        BuilderDemo.run();

        System.out.println("\n=== PROTOTYPE TEST ===");
        PrototypeDemo.run();
    }
}
