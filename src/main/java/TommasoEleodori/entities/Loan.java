package TommasoEleodori.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @Column(name = "loan_start_date")
    private LocalDate loanStartDate;
    @Column(name = "loan_expected_return_date")
    private LocalDate loanExpectedReturnDate;
    @Column(name = "loan_effective_return_date")
    private LocalDate loanEffectiveReturnDate;

    public Loan() {
    }

    public Loan(User user, Lecture lecture) {
        if (user != null && lecture != null) {
            this.user = user;
            this.lecture = lecture;
            this.loanStartDate = LocalDate.now();
            this.loanExpectedReturnDate = loanStartDate.plusDays(30);
        } else throw new IllegalArgumentException("the fields user and lecture must not be null");
    }

    public Loan(User user, Lecture lecture, LocalDate loanStartDate) {
        if (user != null && lecture != null && loanStartDate != null && !loanStartDate.isAfter(LocalDate.now())) {
            this.user = user;
            this.lecture = lecture;
            this.loanStartDate = loanStartDate;
            this.loanExpectedReturnDate = loanStartDate.plusDays(30);
        } else throw new IllegalArgumentException("The fields user, lecture, and loanStartDate must not be null." +
                " Additionally, loanStartDate cannot be set to a future date. ");
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getLoanExpectedReturnDate() {
        return loanExpectedReturnDate;
    }

    public LocalDate getLoanEffectiveReturnDate() {
        return loanEffectiveReturnDate;
    }

    public void setLoanEffectiveReturnDate(LocalDate loanEffectiveReturnDate) {
        this.loanEffectiveReturnDate = loanEffectiveReturnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", lecture=" + lecture +
                ", loanStartDate=" + loanStartDate +
                ", loanExpectedReturnDate=" + loanExpectedReturnDate +
                ", loanEffectiveReturnDate=" + loanEffectiveReturnDate +
                '}';
    }
}
