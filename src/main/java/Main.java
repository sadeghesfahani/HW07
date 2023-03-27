import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.Author;
import entity.Book;
import service.AuthorService;
import service.BookService;
import java.sql.Date;
import javax.sql.DataSource;
import java.sql.*;

public class Main {
    static HikariConfig config = new HikariConfig();
    public static void main(String[] args) throws SQLException {

        config.setJdbcUrl("jdbc:postgresql://localhost:5456/db_legal");
        config.setUsername("admin");
        config.setPassword("admin");


        DataSource dataSource = new HikariDataSource(config);

        // create AuthorService and BookService objects
        AuthorService authorService = new AuthorService(config);
        BookService bookService = new BookService(config);

        // register a new author
        Author author = authorService.register("John", "Doe", 35);

        // add a new book for the author
        Date publishYear = new Date(2022);
        Book book = bookService.addBook("The Book Title", 2022, author.getId());

    }
}
