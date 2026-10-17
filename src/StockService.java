
import java.sql.SQLException;
import java.util.List;

public class StockService {

    private final StockRepository stockRepository;

    public StockService() {
        this.stockRepository = new StockRepository();
    }

    // Add one or multiple stock items
    public void addStock(Stock... stocks) throws SQLException {
        for (Stock stock : stocks) {
            if (stock.getStock_quantity() < 0) {
                throw new IllegalArgumentException(
                        "Quantity cannot be negative");
            }

            stockRepository.addStock(stock);
            System.out.println("Stock records inserted.");
        }
    }
    

    // View all stock
    public List<Stock> getStocks() throws SQLException {

        return stockRepository.getStocks();
    }

    // Search by ID
    public Stock searchStock(long stockId) throws SQLException {
        return stockRepository.searchStock(stockId);
    }

    // Search by name
    public List<Stock> searchStock(String stockName)
            throws SQLException {
        return stockRepository.searchStock(stockName);
    }

    // Delete stock
    public boolean deleteStock(long stockId) throws SQLException {
        return stockRepository.deleteStock(stockId);
    }

    // Update quantity
    public boolean updateStock(long stockId, long newQuantity)
            throws SQLException {
        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative");
        }

        return stockRepository.updateStock(stockId, newQuantity);
    }
}
