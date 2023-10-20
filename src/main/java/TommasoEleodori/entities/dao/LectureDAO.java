package TommasoEleodori.entities.dao;

import TommasoEleodori.entities.Lecture;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class LectureDAO {
    private final EntityManager em;

    public LectureDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Lecture lecture) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(lecture);
        transaction.commit();
        System.out.println("nuova lecture salvata");
    }

    public Lecture getByIdString(String isbn) {
        UUID uuid = UUID.fromString(isbn);
        return em.find(Lecture.class, uuid);

    }

    public void deleteByIdString(String isbn) {
        UUID uuid = UUID.fromString(isbn);
        Lecture found = em.find(Lecture.class, uuid);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("The lecture has been deleted");
        } else {
            System.err.println("the lecture dosen't exist");
        }
    }

    public void delete(Lecture lecture) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.remove(lecture);
        transaction.commit();
        System.out.println("lecture cancellata");
    }

    public Lecture refresh(Lecture lecture) {
        em.refresh(lecture);
        return lecture;
    }

    public List<Lecture> findByPublicationYear(int year) {
        TypedQuery<Lecture> getByYear = em.createQuery("SELECT l FROM Lecture l WHERE publicationYear = :year ", Lecture.class);
        getByYear.setParameter("year", year);
        return getByYear.getResultList();
    }

    public List<Lecture> findByAuthor(String author) {
        TypedQuery<Lecture> getByAuthor = em.createQuery("SELECT l FROM Lecture l WHERE LOWER(l.author) LIKE :author ", Lecture.class);
        getByAuthor.setParameter("author", author);
        return getByAuthor.getResultList();
    }


    public List<Lecture> finfByTitleOrPartial(String title) {
        TypedQuery<Lecture> getByTitle = em.createQuery("SELECT l FROM Lecture l WHERE LOWER(l.title) LIKE LOWER(:title) ", Lecture.class);
        getByTitle.setParameter("title", "%" + title + "%");
        return getByTitle.getResultList();
    }
}
