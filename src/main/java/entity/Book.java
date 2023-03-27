package entity;


import java.sql.Date;

public class Book {
    private String title;
    private int id;
    private int year;
    private Author author;

    public Book(String title, int id, int year, Author author) {
        this.title = title;
        this.id = id;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
