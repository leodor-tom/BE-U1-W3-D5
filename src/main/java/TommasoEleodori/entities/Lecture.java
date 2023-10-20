package TommasoEleodori.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "lectures")
@DiscriminatorColumn(name = "lecture_type")
public abstract class Lecture {
    @Id
    @GeneratedValue
    private UUID ibsn;
    private
}
