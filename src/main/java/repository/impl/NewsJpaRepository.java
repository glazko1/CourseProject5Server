package repository.impl;

import entity.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.NewsRepository;
import repository.exception.RepositoryException;
import util.config.AppConfig;

import java.util.List;

public class NewsJpaRepository implements NewsRepository {

    private static final NewsJpaRepository INSTANCE = new NewsJpaRepository();

    public static NewsJpaRepository getInstance() {
        return INSTANCE;
    }

    private NewsJpaRepository() {}

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<News> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Basket", News.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}