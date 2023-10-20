package TommasoEleodori;

import TommasoEleodori.entities.*;
import TommasoEleodori.entities.dao.LectureDAO;
import TommasoEleodori.entities.dao.LoanDao;
import TommasoEleodori.entities.dao.UserDao;
import TommasoEleodori.entities.enums.Genre;
import TommasoEleodori.entities.enums.Periodicity;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static TommasoEleodori.utils.JpaUtil.emf;

public class Application {

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        LectureDAO ld = new LectureDAO(em);
        UserDao ud = new UserDao(em);
        LoanDao lod = new LoanDao(em);

        Book book = new Book("The Mysterious Island", 1874, 650, "Jules Verne", Genre.ADVENTURE);
        Book book1 = new Book("The Enigma of Time", 2020, 450, "Jane Doe", Genre.MYSTERY);
        Magazine magazine = new Magazine("Tech Today", 2021, 120, Periodicity.MONTHLY);
        Magazine magazine1 = new Magazine("Tech Insights", 2022, 80, Periodicity.WEEKLY);

        User user = new User("John", "Doe", LocalDate.of(1990, 05, 15));
        User user1 = new User("Emily", "Johnson", LocalDate.of(1998, 12, 15));
        User user2 = new User("Emily", "Williams", LocalDate.of(2000, 04, 15));



       /* ld.save(book);
        ld.save(magazine);
        ud.save(user);
        ud.save(user1);
        ld.save(book1);
        ld.save(magazine1);
        ud.save(user2);
      */
        Lecture techToday = ld.getByIdString("5b57e56f-f0a7-4a49-9c9e-50abf5849606");
        Lecture theMyst = ld.getByIdString("9b9c8585-4730-405c-bf83-2cd8cd14c5fe");
        User john = ud.getByIdString("b3424281-a917-46ee-967e-ad45786abb14");
        User williams = ud.getByIdString("870b30b6-4118-4275-8789-9c4606d2ea06");

        Loan loan = new Loan(john, techToday);
        Loan loan1 = new Loan(williams, theMyst, LocalDate.of(2023, 10, 17));
        Loan loan2 = new Loan(john, theMyst, LocalDate.of(2023, 8, 10));
        //lod.save(loan);
        //lod.save(loan1);
        //lod.save(loan2);


        // ld.deleteByIdString("65d414cd-31a2-412b-999d-77d00367d558");
        // ld.findByPublicationYear(2020).forEach(System.out::println);
        //ld.findByAuthor("jules verne").forEach(System.out::println);
        // ld.finfByTitleOrPartial("te").forEach(System.out::println);
        // lod.getActuallyLoanedLectureByCardNumber("b3424281-a917-46ee-967e-ad45786abb14").forEach(System.out::println);
        // lod.getExpiredLoans().forEach(System.out::println);
        try {
            lod.setEffectiveReturnDate(5, LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        em.close();
        emf.close();
    }
}
