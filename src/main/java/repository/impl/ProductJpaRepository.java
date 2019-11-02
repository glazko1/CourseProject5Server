package repository.impl;

import entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.ProductRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

import java.util.List;
import java.util.UUID;

public class ProductJpaRepository implements ProductRepository {

    private static final ProductJpaRepository INSTANCE = new ProductJpaRepository();

    public static ProductJpaRepository getInstance() {
        return INSTANCE;
    }

    private ProductJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public Product get(UUID productId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product WHERE productId = ?1", Product.class)
                    .setParameter(1, productId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Product> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}