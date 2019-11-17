package repository.impl;

import entity.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.AddressRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

public class AddressJpaRepository implements AddressRepository {

    private static final AddressJpaRepository INSTANCE = new AddressJpaRepository();

    public static AddressJpaRepository getInstance() {
        return INSTANCE;
    }

    private AddressJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public Address get(int addressId) throws RepositoryException {
        return null;
    }

    @Override
    public Address get(String region, String locality, String street,
                       int houseNumber, int flatNumber) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Address WHERE region = ?1 AND " +
                    "locality = ?2 AND street = ?3 AND house = ?4 AND flat = ?5", Address.class)
                    .setParameter(1, region)
                    .setParameter(2, locality)
                    .setParameter(3, street)
                    .setParameter(4, houseNumber)
                    .setParameter(5, flatNumber)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Address address) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(address);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }
}