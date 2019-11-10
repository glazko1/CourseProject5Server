package repository.impl;

import entity.Department;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.DepartmentRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

public class DepartmentJpaRepository implements DepartmentRepository {

    private static final DepartmentJpaRepository INSTANCE = new DepartmentJpaRepository();

    public static DepartmentJpaRepository getInstance() {
        return INSTANCE;
    }

    private DepartmentJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public Department get(int departmentId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department WHERE departmentId = ?1", Department.class)
                    .setParameter(1, departmentId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}