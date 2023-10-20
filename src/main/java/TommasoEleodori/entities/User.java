package TommasoEleodori.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "card_number")
    private UUID cardNumber;
    private String name;
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private Set<Loan> loans;

    public User() {
    }

    public User(String name, String surname, LocalDate dateOfBirth) {
        if (name != null && !name.trim().isEmpty() && surname != null && !surname.trim().isEmpty() &&
                dateOfBirth != null) {
            Period age = Period.between(dateOfBirth, LocalDate.now());
            if (age.getYears() >= 14) {
                this.name = name;
                this.surname = surname;
                this.dateOfBirth = dateOfBirth;
            } else {
                throw new IllegalArgumentException("the user must be at least 14 years old");
            }
        } else {
            throw new IllegalArgumentException("the field's values can't be null or empty");
        }
    }

    public UUID getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "User{" +
                "cardNumber=" + cardNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
