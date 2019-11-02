package repository.impl;

import entity.Basket;
import entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.BasketRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

import java.util.UUID;

public class BasketJpaRepository implements BasketRepository {

    private static final BasketJpaRepository INSTANCE = new BasketJpaRepository();

    public static BasketJpaRepository getInstance() {
        return INSTANCE;
    }

    private BasketJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public Basket get(UUID userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT b FROM Basket b JOIN User u ON b.user.userId = u.userId", Basket.class)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void addProduct(Basket basket, Product product) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            basket.addProduct(product);
            session.update(basket);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void removeProduct(Basket basket, Product product) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            basket.removeProduct(product);
            session.update(basket);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }
}