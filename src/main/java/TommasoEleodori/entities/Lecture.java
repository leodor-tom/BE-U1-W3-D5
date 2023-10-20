package TommasoEleodori.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "lectures")
@DiscriminatorColumn(name = "lecture_type")
public abstract class Lecture {
    @Id
    @GeneratedValue
    private UUID ibsn;
    private String title;

    @Column(name = "publication_year")
    private int publicationYear;
    @Column(name = "pages_number")
    private int pagesNumber;

    @OneToMany(mappedBy = "lecture")
    private Set<Loan> loans;

    public Lecture() {
    }

    public Lecture(String title, int publicationYear, int pagesNumber) {
        if (title != null && !title.trim().isEmpty() && publicationYear >= 0 &&
                pagesNumber >= 0) {
            this.title = title;
            this.publicationYear = publicationYear;
            this.pagesNumber = pagesNumber;
        } else throw new IllegalArgumentException("the values can't be null, empty or less than 0");
    }


    public UUID getIbsn() {
        return ibsn;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "ibsn=" + ibsn +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", pagesNumber=" + pagesNumber +
                '}';
    }
}
