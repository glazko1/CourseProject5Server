package repository.impl;

import entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ProductRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

import java.util.List;

public class ProductJpaRepository implements ProductRepository {

    private static final ProductJpaRepository INSTANCE = new ProductJpaRepository();

    public static ProductJpaRepository getInstance() {
        return INSTANCE;
    }

    private ProductJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<Product> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Product get(int productId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product WHERE productId = ?1", Product.class)
                    .setParameter(1, productId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Product product) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }
}