package repository;

import entity.Author;

import java.sql.*;

public class AuthorRepository {
    private Connection connection;

    public AuthorRepository(Connection connection) {
        this.connection = connection;
    }


    public void add(Author author) throws SQLException {
        String sql = "INSERT INTO author (name, surname, age) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, author.getName());
        pstmt.setString(2, author.getSurname());
        pstmt.setInt(3, author.getAge());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            author.setId(id);
        }
    }





    public Author load(int authorId) {

        String sql = "SELECT * FROM author WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                Author author = new Author(name, surname, age);
                author.setId(authorId);
                return author;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
