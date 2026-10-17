
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockRepository {

    // Add stock
    public void addStock(Stock stock) throws SQLException {
        String sql = """
        INSERT INTO stocks
        (stock_name, stock_price, stock_quantity, stock_type)
        VALUES (?, ?, ?, ?)
        """;

        try (Connection connection = PsqlJDBCdbCon.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, stock.getStock_name());
            statement.setDouble(2, stock.getStock_price());
            statement.setLong(3, stock.getStock_quantity());
            statement.setString(4, stock.getStock_type());

            statement.executeUpdate();
        }
    }

    // View all stock
    public List<Stock> getStocks() throws SQLException {
        List<Stock> stocks = new ArrayList<>();

        String sql = """
                SELECT stock_id, stock_name, stock_price,
                       stock_quantity, stock_type
                FROM stocks
                ORDER BY stock_id
                """;

        try (Connection connection = PsqlJDBCdbCon.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                stocks.add(mapStock(resultSet));
            }
        }

        return stocks;
    }

    // Search by ID
    public Stock searchStock(long stockId) throws SQLException {
        String sql = "SELECT * FROM stocks WHERE stock_id = ?";

        try (Connection connection = PsqlJDBCdbCon.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(1, stockId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapStock(resultSet);
                }
            }
        }

        return null;
    }

    // Search by name
    public List<Stock> searchStock(String stockName)
            throws SQLException {

        List<Stock> stocks = new ArrayList<>();
        String sql = """
                SELECT * FROM stocks
                WHERE stock_name ILIKE ?
                ORDER BY stock_id
                """;

        try (Connection connection = PsqlJDBCdbCon.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, stockName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    stocks.add(mapStock(resultSet));
                }
            }
        }

        return stocks;
    }

    // Delete stock
    public boolean deleteStock(long stockId) throws SQLException {
        String sql = "DELETE FROM stocks WHERE stock_id = ?";

        try (Connection connection = PsqlJDBCdbCon.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(1, stockId);
            return statement.executeUpdate() > 0;
        }
    }

    // Update quantity
    public boolean updateStock(long stockId, long newQuantity)
            throws SQLException {

        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative");
        }

        String sql = """
                UPDATE stocks
                SET stock_quantity = ?
                WHERE stock_id = ?
                """;

        try (Connection connection = PsqlJDBCdbCon.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(1, newQuantity);
            statement.setLong(2, stockId);

            return statement.executeUpdate() > 0;
        }
    }

    // Convert a database row into a Stock object
    private Stock mapStock(ResultSet resultSet) throws SQLException {
        Stock stock = new Stock(
                resultSet.getString("stock_name"),
                resultSet.getDouble("stock_price"),
                resultSet.getLong("stock_quantity"),
                resultSet.getString("stock_type")
        );

        return stock;
    }
}
