package service.impl;

import entity.Address;
import entity.Basket;
import entity.Department;
import entity.News;
import entity.Order;
import entity.OrderStatus;
import entity.Product;
import entity.User;
import entity.UserStatus;
import repository.AddressRepository;
import repository.BasketRepository;
import repository.DepartmentRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserRepository;
import repository.exception.RepositoryException;
import repository.impl.AddressJpaRepository;
import repository.impl.BasketJpaRepository;
import repository.impl.DepartmentJpaRepository;
import repository.impl.OrderJpaRepository;
import repository.impl.ProductJpaRepository;
import repository.impl.UserJpaRepository;
import service.UserService;
import service.exception.ServiceException;
import util.validator.OrderInformationValidator;
import util.validator.UserInformationValidator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl() {}

    private UserRepository userRepository = UserJpaRepository.getInstance();
    private ProductRepository productRepository = ProductJpaRepository.getInstance();
    private BasketRepository basketRepository = BasketJpaRepository.getInstance();
    private DepartmentRepository departmentRepository = DepartmentJpaRepository.getInstance();
    private OrderRepository orderRepository = OrderJpaRepository.getInstance();
    private AddressRepository addressRepository = AddressJpaRepository.getInstance();
    private UserInformationValidator userValidator = UserInformationValidator.getInstance();
    private OrderInformationValidator orderValidator = OrderInformationValidator.getInstance();

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
                new UserStatus(2, "Пользователь"),
                email, false, avatarNumber, password);
        try {
            userRepository.add(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(int userId) throws ServiceException {
        try {
            return userRepository.get(userId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return null;
    }

    @Override
    public void changeAvatar(int userId, int avatarNumber) throws ServiceException {

    }

    @Override
    public void changeEmail(int userId, String newEmail) throws ServiceException {

    }

    @Override
    public void changePassword(int userId, String currentPassword, String newPassword) throws ServiceException {

    }

    @Override
    public void restorePassword(int userId, String password) throws ServiceException {

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
    public List<Department> getAllDepartments() throws ServiceException {
        try {
            return departmentRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getAllNews() throws ServiceException {
        return null;
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

    @Override
    public void addOrder(int userId, String region, String locality,
                         String street, int houseNumber, int flatNumber) throws ServiceException {
        if (!orderValidator.validate(region, locality, street, houseNumber, flatNumber)) {
            throw new ServiceException("Information is not valid!");
        }
        try {
            Basket basket = basketRepository.get(userId);
            List<Product> products = basket.getProducts();
            Map<Product, Integer> productMap = new HashMap<>();
            products.forEach(product -> {
                if (productMap.containsKey(product)) {
                    productMap.replace(product, productMap.get(product) + 1);
                } else {
                    productMap.put(product, 1);
                }
            });
            final boolean[] correct = {true};
            productMap.forEach((product, amount) -> {
                if (amount > product.getAmount()) {
                    correct[0] = false;
                }
            });
            if (!correct[0]) {
                throw new ServiceException("One or more products selected in amount bigger than present!");
            }
            Address address = addressRepository.get(region, locality,
                    street, houseNumber, flatNumber);
            if (address == null) {
                addressRepository.add(new Address(region, locality,
                        street, houseNumber, flatNumber));
                address = addressRepository.get(region, locality,
                        street, houseNumber, flatNumber);
            }
            User user = userRepository.get(userId);
            double orderSum = products.stream()
                    .mapToDouble(Product::getPrice)
                    .sum();
            productMap.forEach((product, amount) -> {
                product.setAmount(product.getAmount() - amount);
                try {
                    productRepository.update(product);
                } catch (RepositoryException e) {
                    System.out.println(e.getMessage());
                }
            });
            orderRepository.add(new Order(user, new OrderStatus(1, "В обработке"),
                    address, new Date(System.currentTimeMillis()), orderSum, products));
            basketRepository.clear(basket);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrders(int userId) throws ServiceException {
        try {
            return orderRepository.getByUserId(userId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}