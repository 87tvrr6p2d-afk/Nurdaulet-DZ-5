public class Discount implements Cloneable {
    private String code;
    private double percent; // 10 = 10%

    public Discount(String code, double percent) {
        this.code = code;
        this.percent = percent;
    }

    public double apply(double amount) {
        return amount * (1 - percent / 100.0);
    }

    @Override
    public Discount clone() {
        try {
            return (Discount) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return code + " (" + percent + "%)";
    }
}
