package service.impl;

import entity.Basket;
import entity.Product;
import entity.User;
import entity.UserStatus;
import repository.BasketRepository;
import repository.ProductRepository;
import repository.UserRepository;
import repository.exception.RepositoryException;
import repository.impl.BasketJpaRepository;
import repository.impl.ProductJpaRepository;
import repository.impl.UserJpaRepository;
import service.UserService;
import service.exception.ServiceException;
import util.validator.UserInformationValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl() {}

    private UserRepository userRepository = UserJpaRepository.getInstance();
    private ProductRepository productRepository = ProductJpaRepository.getInstance();
    private BasketRepository basketRepository = BasketJpaRepository.getInstance();
    private UserInformationValidator userValidator = UserInformationValidator.getInstance();

    @Override
    public User signIn(String username, String password) throws ServiceException {
        if (!userValidator.validate(username)) {
            throw new ServiceException("Information is not valid!");
        }
        try {
            User user = userRepository.get(username, password);
            if (user.isBanned()) {
                throw new ServiceException("User is banned!");
            }
            return user;
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signUp(String username, String firstName, String lastName,
                       String email, String password, int avatarNumber) throws ServiceException {
        if (!userValidator.validate(username, firstName, lastName, email)) {
            throw new ServiceException("Information is not valid!");
        }
        User user = new User(username, firstName, lastName,
                new UserStatus(2, "User"),
                email, false, avatarNumber, password);
        try {
            userRepository.add(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        try {
            return productRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Basket getBasket(int userId) throws ServiceException {
        try {
            return basketRepository.get(userId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addProductToBasket(int userId, int productId) throws ServiceException {
        try {
            Basket basket = basketRepository.get(userId);
            Product product = productRepository.get(productId);
            basketRepository.addProduct(basket, product);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeProductFromBasket(int userId, int productId) throws ServiceException {
        try {
            Basket basket = basketRepository.get(userId);
            Product product = productRepository.get(productId);
            basketRepository.removeProduct(basket, product);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}