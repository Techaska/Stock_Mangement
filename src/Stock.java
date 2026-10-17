
public class Stock {

    private long stock_id;
    private String stock_name;
    private double stock_price;
    private long stock_quantity;
    private String stock_type;

    public Stock(String stock_name,
                 double stock_price, long stock_quantity,
                 String stock_type) {
        this.stock_name = stock_name;
        this.stock_price = stock_price;
        this.stock_quantity = stock_quantity;
        this.stock_type = stock_type;
    }

    public Stock() {
        // Empty constructor for object initialization.
    }

    public long getStock_id() {
        return stock_id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public double getStock_price() {
        return stock_price;
    }

    public long getStock_quantity() {
        return stock_quantity;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_quantity(long stock_quantity) {
        if (stock_quantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative");
        }
        this.stock_quantity = stock_quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stock_id=" + stock_id +
                ", stock_name='" + stock_name + '\'' +
                ", stock_price=" + stock_price +
                ", stock_quantity=" + stock_quantity +
                ", stock_type='" + stock_type + '\'' +
                '}';
    }
}
