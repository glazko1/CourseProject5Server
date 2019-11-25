package repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.config.AppConfig;
import entity.User;
import org.hibernate.SessionFactory;
import repository.UserRepository;
import repository.exception.RepositoryException;

import java.util.List;

public class UserJpaRepository implements UserRepository {

    private static final UserJpaRepository INSTANCE = new UserJpaRepository();

    public static UserJpaRepository getInstance() {
        return INSTANCE;
    }

    private UserJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<User> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public User get(String username, String password) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE username = ?1 AND password = ?2", User.class)
                    .setParameter(1, username)
                    .setParameter(2, password)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public User get(int userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE userId = ?1", User.class)
                    .setParameter(1, userId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public User getByUsername(String username) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE username = ?1", User.class)
                    .setParameter(1, username)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public User getByEmail(String email) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE email = ?1", User.class)
                    .setParameter(1, email)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(User user) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(User user) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }
}