package service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.Author;
import entity.Book;
import repository.AuthorRepository;
import repository.BookRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class BookService {
    private Connection connection;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService(HikariConfig config) throws SQLException {
        DataSource dataSource = new HikariDataSource(config);
        connection = dataSource.getConnection();
        this.bookRepository = new BookRepository(connection);
        this.authorRepository = new AuthorRepository(connection);
    }

    public void addBook(String title, Date publicationYear, int authorId) {
        // Load author from repository
        Author author = authorRepository.load(authorId);
        if (author == null) {
            throw new IllegalArgumentException("Author with id " + authorId + " not found");
        }

        // Create book object
        Book book = new Book(title, 10, publicationYear, author);

        // Save book to repository
        bookRepository.save(book);
    }
}
