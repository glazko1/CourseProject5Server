package repository.impl;

import entity.UserStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.UserStatusRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

public class UserStatusJpaRepository implements UserStatusRepository {

    private static final UserStatusJpaRepository INSTANCE = new UserStatusJpaRepository();

    public static UserStatusJpaRepository getInstance() {
        return INSTANCE;
    }

    private UserStatusJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public UserStatus get(int userStatusId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE statusId = ?1", UserStatus.class)
                    .setParameter(1, userStatusId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}