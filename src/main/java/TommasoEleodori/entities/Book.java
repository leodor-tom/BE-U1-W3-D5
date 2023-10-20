package TommasoEleodori.entities;

import TommasoEleodori.entities.enums.Genre;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
public class Book extends Lecture {
    private String author;
    private Genre genre;

    public Book() {
    }

    public Book(String author, Genre genre) {
        super();
        if (author != null || author.trim().isEmpty() || genre != null) {
            this.author = author;
            this.genre = genre;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre=" + genre +
                '}' + super.toString();
    }
}
