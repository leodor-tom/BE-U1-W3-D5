package TommasoEleodori.entities.dao;

import TommasoEleodori.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class UserDao {
    private final EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        System.out.println("nuovo user salvato");
    }

    public User getByIdString(String cardNumber) {
        UUID uuid = UUID.fromString(cardNumber);
        return em.find(User.class, uuid);
    }

    public void delete(User user) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.remove(user);
        transaction.commit();
        System.out.println("user cancellato");
    }

    public User refresh(User user) {
        em.refresh(user);
        return user;
    }
}
