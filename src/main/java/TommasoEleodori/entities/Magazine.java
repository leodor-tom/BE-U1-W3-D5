package TommasoEleodori.entities;

import TommasoEleodori.entities.enums.Periodicity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Lecture {
    private Periodicity periodicity;

    public Magazine() {
    }

    public Magazine(Periodicity periodicity) {
        super();
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
