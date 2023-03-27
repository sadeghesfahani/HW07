CREATE DATABASE your_database_name;
CREATE TABLE author (
                        author_id SERIAL PRIMARY KEY,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        age INT NOT NULL
);

CREATE TABLE book (
                      book_id SERIAL PRIMARY KEY,
                      title VARCHAR(100) NOT NULL,
                      year_published Date NOT NULL,
                      author_id INT NOT NULL,
                      FOREIGN KEY (author_id) REFERENCES author (author_id)
);