package repository;

import entity.Author;
import entity.Book;

import java.sql.*;

public class BookRepository {
    private Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO book (title,year_published , author_id) VALUES (?, ?, ?)"
            );
            statement.setString(1, book.getTitle());
            statement.setDate(2, book.getYear());
            statement.setInt(3, book.getAuthor().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving book: " + e.getMessage());
        }
    }

    public Book load(int bookId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM book WHERE id = ?"
            );
            statement.setInt(1, bookId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                Date year = rs.getDate("year");
                int authorId = rs.getInt("author_id");
                Author author = loadAuthor(authorId);
                return new Book(title, bookId, year, author);
            }
        } catch (SQLException e) {
            System.err.println("Error loading book: " + e.getMessage());
        }
        return null;
    }


    private Author loadAuthor(int authorId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM author WHERE author_id = ?"
        );
        statement.setInt(1, authorId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int age = rs.getInt("age");
            Author newAuthor = new Author(firstName, lastName, age);
            newAuthor.setId(authorId);
            return newAuthor;
        }
        return null;
    }

    public void delete(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM book WHERE book_id = ?"
            );
            statement.setInt(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
}
