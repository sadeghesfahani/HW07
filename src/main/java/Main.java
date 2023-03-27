import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static HikariConfig config = new HikariConfig();
    public static void main(String[] args) {

        config.setJdbcUrl("jdbc:postgresql://localhost:5456/db_legal");
        config.setUsername("admin");
        config.setPassword("admin");


        DataSource dataSource = new HikariDataSource(config);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM answer")) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
