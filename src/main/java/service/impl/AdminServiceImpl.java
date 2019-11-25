package service.impl;

import entity.Department;
import entity.Order;
import entity.OrderStatus;
import entity.Product;
import entity.User;
import entity.UserStatus;
import repository.DepartmentRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserRepository;
import repository.UserStatusRepository;
import repository.exception.RepositoryException;
import repository.impl.DepartmentJpaRepository;
import repository.impl.OrderJpaRepository;
import repository.impl.ProductJpaRepository;
import repository.impl.UserJpaRepository;
import repository.impl.UserStatusJpaRepository;
import service.AdminService;
import service.exception.ServiceException;
import util.validator.ProductInformationValidator;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private static final AdminServiceImpl INSTANCE = new AdminServiceImpl();

    public static AdminServiceImpl getInstance() {
        return INSTANCE;
    }

    private AdminServiceImpl() {}

    private ProductRepository productRepository = ProductJpaRepository.getInstance();
    private DepartmentRepository departmentRepository = DepartmentJpaRepository.getInstance();
    private OrderRepository orderRepository = OrderJpaRepository.getInstance();
    private UserRepository userRepository = UserJpaRepository.getInstance();
    private UserStatusRepository userStatusRepository = UserStatusJpaRepository.getInstance();
    private ProductInformationValidator productValidator = ProductInformationValidator.getInstance();

    @Override
    public void addProduct(String productName, int departmentId,
                           double price, int amount) throws ServiceException {
        if (!productValidator.validate(productName, price, amount)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {
            Product existingProduct = productRepository.get(productName);
            if (existingProduct == null) {
                Department department = departmentRepository.get(departmentId);
                String imagePath = "file:/E:/Java/CourseProject5/Server/src/main/resources/img/" + productName + ".jpg";
                Product product = new Product(productName, department, imagePath, price, amount);
                productRepository.add(product);
            } else {
                throw new ServiceException("Товар с таким названием уже существует! Повторите ввод");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editProduct(int productId, String productName, int departmentId,
                            double price, int amount) throws ServiceException {
        if (!productValidator.validate(productName, price, amount)) {
            throw new ServiceException("Информация некорректна! Пожалуйста, повторите ввод.");
        }
        try {
            Product existingProduct = productRepository.get(productName);
            if (existingProduct == null) {
                Department department = departmentRepository.get(departmentId);
                Product product = productRepository.get(productId);
                product.setProductName(productName);
                product.setDepartment(department);
                product.setPrice(price);
                product.setAmount(amount);
                productRepository.update(product);
            } else {
                throw new ServiceException("Товар с таким названием уже существует! Повторите ввод");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws ServiceException {
        try {
            Product product = productRepository.get(productId);
            product.setAmount(0);
            productRepository.update(product);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try {
            return orderRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void processOrder(int orderId) throws ServiceException {
        try {
            Order order = orderRepository.get(orderId);
            order.setOrderStatus(new OrderStatus(2, "Обработан"));
            orderRepository.update(order);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeBanStatus(int userId) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            user.setBanned(!user.isBanned());
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeUserStatus(int userId, int statusId) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            UserStatus userStatus = userStatusRepository.get(statusId);
            user.setUserStatus(userStatus);
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}