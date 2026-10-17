import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PsqlJDBCdbCon {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/stockmdb";
        String user = "postgres";
        String password = System.getenv("DB_PASSWORD_PSQL");

        return DriverManager.getConnection(url, user, password);
    }
}