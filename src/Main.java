import java.sql.SQLException;
import java.util.List;

public class Main  {
    public static void main(String[] args) throws SQLException {

        StockService stockService = new StockService();

        Stock[] sampleStocks = {
                new Stock("10k E", 20.20, 10, "PTH"),
                new Stock("20k E", 20.20, 100, "SMD"),
                new Stock("30k E", 20.20, 1000, "PTH"),
                new Stock("Resistor 1k", 1.50, 500, "SMD"),
                new Stock("Capacitor 10uF", 3.25, 200, "SMD"),
                new Stock("LED Red", 2.00, 150, "PTH"),
                new Stock("Diode 1N4007", 1.80, 300, "PTH"),
                new Stock("Transistor BC547", 4.50, 100, "PTH"),
                new Stock("IC NE555", 12.00, 50, "DIP"),
                new Stock("Connector 2-pin", 5.00, 75, "PTH")
        };

        // Add multiple stocks
//        stockService.addStock(sampleStocks);


        // READ: Display all existing stocks
        System.out.println("\n===== READ ALL STOCKS =====");

        for (Stock stock : stockService.getStocks()) {
            System.out.println(stock);
        }

        // SEARCH: Find existing stock by ID
        System.out.println("\n===== SEARCH BY ID =====");

        long stockId = 1;

        Stock foundStock = stockService.searchStock(stockId);

        if (foundStock != null) {
            System.out.println("Found: " + foundStock);
        } else {
            System.out.println("Stock ID " + stockId + " not found.");
        }

        // SEARCH: Find existing stocks by name
        System.out.println("\n===== SEARCH BY NAME =====");

        List<Stock> searchResults =
                stockService.searchStock("10k E");

        for (Stock stock : searchResults) {
            System.out.println(stock);
        }

        // UPDATE: Change quantity of stock ID 1
        System.out.println("\n===== UPDATE QUANTITY =====");

        if (foundStock != null) {
            long newQuantity = 15;

            boolean updated =
                    stockService.updateStock(stockId, newQuantity);

            System.out.println("Updated: " + updated);
            System.out.println(stockService.searchStock(stockId));
        }

        // DELETE
        System.out.println("\n===== DELETE TEST =====");

        Stock stockToDelete = stockService.searchStock(12);

        if (stockToDelete != null) {
            boolean deleted = stockService.deleteStock(12);

            if (deleted) {
                System.out.println("Stock deleted successfully.");
            } else {
                System.out.println("Stock was not deleted.");
            }
        } else {
            System.out.println("Stock ID 12 not found. Nothing to delete.");
        }

        // READ: Display final database contents
        System.out.println("\n===== FINAL STOCK LIST =====");

        for (Stock stock : stockService.getStocks()) {
            System.out.println(stock);
        }

    }
}