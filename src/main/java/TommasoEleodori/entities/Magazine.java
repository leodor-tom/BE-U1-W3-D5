package TommasoEleodori.entities;

import TommasoEleodori.entities.enums.Periodicity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Lecture {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine() {
    }

    public Magazine(String title, int publicationYear, int pagesNumber, Periodicity periodicity) {
        super(title, publicationYear, pagesNumber);
        if (periodicity != null) this.periodicity = periodicity;
        else throw new IllegalArgumentException("periodicity can't be null");
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                '}' + super.toString();
    }
}
