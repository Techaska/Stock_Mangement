
import java.util.ArrayList;
import java.util.Objects;

public class StockService {

    private final ArrayList<Stock> stocks = new ArrayList<>();

    // Add stock

    public void addStock(Stock... stocks) {
        for (Stock stock : stocks) {
            this.stocks.add(stock);
        }
    }


    // View all stock
    public void getStocks() {
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
    }

    // Search stock
    public Stock searchStock(long stockId) {

        if (stocks.isEmpty()) {
            System.out.println("No stocks found");
            return null;
        }

        for (Stock stock : stocks) {
            if (stock.getStock_id() == stockId) {
                return stock;
            }
        }

        System.out.println("Stock not found");
        return null;
    }

    public Stock searchStock(String stockade) {

        if (stocks.isEmpty()) {
            System.out.println("No stocks found");
            return null;
        }

        for (Stock stock : stocks) {
            if (stock.getStock_name().equals(stockade)) {
                return stock;
            }
        }

        System.out.println("Stock not found");
        return null;
    }


    // Delete stock

    public boolean deleteStock(long stockId) {

        if (stocks.isEmpty()) {
            System.out.println("No stocks found");
            return false;
        }

        for (Stock stock : stocks) {
            if (stock.getStock_id() == stockId) {
                stocks.remove(stock);
                return true;
            }
        }

        System.out.println("Stock not found");
        return false;
    }


    // Update stock
    public boolean updateStock(long stockId, long newQuantity) {

        if (stocks.isEmpty()) {
            System.out.println("No stocks found");
            return false;
        }

        for (Stock stock : stocks) {
            if (stock.getStock_id() == stockId) {
                stock.setStock_quantity(newQuantity);
                return true;
            }
        }

        System.out.println("Stock not found");
        return false;
    }
}
