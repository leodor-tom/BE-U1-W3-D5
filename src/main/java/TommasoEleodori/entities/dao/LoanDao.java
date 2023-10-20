package TommasoEleodori.entities.dao;

import TommasoEleodori.entities.Lecture;
import TommasoEleodori.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LoanDao {
    private final EntityManager em;

    public LoanDao(EntityManager em) {
        this.em = em;
    }

    public void save(Loan prestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(prestito);
        transaction.commit();
        System.out.println("nuovo prestito salvato");
    }

    public Loan getById(long id) {
        return em.find(Loan.class, id);
    }

    public void delete(Loan prestito) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.remove(prestito);
        transaction.commit();
        System.out.println("prestito cancellato");
    }

    public Loan refresh(Loan prestito) {
        em.refresh(prestito);
        return prestito;
    }

    public void setEffectiveReturnDate(long loanId, LocalDate effectiveReturndate) throws Exception {
        Loan loan = em.find(Loan.class, loanId);
        if (loan == null) {
            throw new Exception("Loan not found");
        }
        loan.setLoanEffectiveReturnDate(effectiveReturndate);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(loan);
        transaction.commit();
    }

    public List<Lecture> getActuallyLoanedLectureByCardNumber(String cardNumber) {
        UUID uuid = UUID.fromString(cardNumber);
        TypedQuery<Lecture> getActuallyLoaned = em.createQuery("SELECT l.lecture From Loan l Where l.user.cardNumber = :uuid AND l.loanEffectiveReturnDate IS NULL", Lecture.class);
        getActuallyLoaned.setParameter("uuid", uuid);
        return getActuallyLoaned.getResultList();
    }

    public List<Loan> getExpiredLoans() {
        TypedQuery<Loan> getExpiredLoans = em.createQuery("SELECT l FROM Loan l WHERE l.loanExpectedReturnDate < :currentDate AND l.loanEffectiveReturnDate IS NULL", Loan.class);
        getExpiredLoans.setParameter("currentDate", LocalDate.now());
        return getExpiredLoans.getResultList();
    }
}
