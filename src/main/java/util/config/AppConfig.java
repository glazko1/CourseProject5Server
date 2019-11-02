package util.config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    private AppConfig() {}

    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = sessionFactory();
    }

    private SessionFactory sessionFactory() {
        Configuration configuration = configuration();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration configuration() {
        Configuration configuration = new Configuration();
        configuration.setProperties(hibernateProperties());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserStatus.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Basket.class);
        return configuration;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(Environment.DRIVER, "org.postgresql.Driver");
        hibernateProperties.put(Environment.URL, "jdbc:postgresql://localhost/courseproject5");
        hibernateProperties.put(Environment.USER, "postgres");
        hibernateProperties.put(Environment.PASS, "relbud2k1");
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "none");
        return hibernateProperties;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}