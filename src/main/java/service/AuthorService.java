package service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.Author;
import repository.AuthorRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class AuthorService {
    private Connection connection;
    private AuthorRepository authorRepository;

    public AuthorService(HikariConfig config) throws SQLException {
        DataSource dataSource = new HikariDataSource(config);
        connection = dataSource.getConnection();
        authorRepository = new AuthorRepository(connection);
    }

    public void register(String firstName, String lastName, int age) throws SQLException {
        Author author = new Author(firstName, lastName, age);
        authorRepository.add(author);
    }


}
