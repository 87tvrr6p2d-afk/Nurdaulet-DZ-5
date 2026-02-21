import java.util.ArrayList;
import java.util.List;

public class Order implements Cloneable {
    private List<Product> products = new ArrayList<>();
    private double deliveryCost;
    private List<Discount> discounts = new ArrayList<>();
    private PaymentMethod paymentMethod;

    public Order(double deliveryCost, PaymentMethod paymentMethod) {
        this.deliveryCost = deliveryCost;
        this.paymentMethod = paymentMethod;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public double calculateTotal() {
        double sum = deliveryCost;
        for (Product p : products) sum += p.total();
        for (Discount d : discounts) sum = d.apply(sum);
        return sum;
    }

    @Override
    public Order clone() {
        try {
            Order cloned = (Order) super.clone();

            cloned.products = new ArrayList<>();
            for (Product p : this.products) cloned.products.add(p.clone());

            cloned.discounts = new ArrayList<>();
            for (Discount d : this.discounts) cloned.discounts.add(d.clone());

            // enums are immutable -> ok
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Order{products=" + products +
                ", deliveryCost=" + deliveryCost +
                ", discounts=" + discounts +
                ", paymentMethod=" + paymentMethod +
                ", total=" + calculateTotal() +
                "}";
    }
}
