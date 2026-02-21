public class PrototypeDemo {
    public static void run() {
        Order template = new Order(1500, PaymentMethod.CARD);
        template.addProduct(new Product("Laptop", 350000, 1));
        template.addProduct(new Product("Mouse", 5000, 2));
        template.addDiscount(new Discount("STUDENT10", 10));

        Order cloned = template.clone();
        cloned.addProduct(new Product("Bag", 12000, 1)); // change clone only

        System.out.println("TEMPLATE:\n" + template);
        System.out.println("\nCLONE:\n" + cloned);
    }
}
