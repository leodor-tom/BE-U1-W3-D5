package TommasoEleodori.entities;

import TommasoEleodori.entities.enums.Genre;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("book")
public class Book extends Lecture {
    private String author;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Book() {
    }

    public Book(String title, int publicationYear, int pagesNumber, String author, Genre genre) {
        super(title, publicationYear, pagesNumber);
        if (author != null && !author.trim().isEmpty() && genre != null) {
            this.author = author;
            this.genre = genre;
        } else throw new IllegalArgumentException("the values can't be null or empty");

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
