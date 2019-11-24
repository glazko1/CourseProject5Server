package service.impl;

import entity.Department;
import entity.Order;
import entity.OrderStatus;
import entity.Product;
import repository.DepartmentRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.exception.RepositoryException;
import repository.impl.DepartmentJpaRepository;
import repository.impl.OrderJpaRepository;
import repository.impl.ProductJpaRepository;
import service.AdminService;
import service.exception.ServiceException;

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

    @Override
    public void addProduct(String productName, int departmentId,
                           double price, int amount) throws ServiceException {
        try {
            Department department = departmentRepository.get(departmentId);
            String imagePath = "file:/E:/Java/CourseProject5/Server/src/main/resources/img/" + productName + ".jpg";
            Product product = new Product(productName, department, imagePath, price, amount);
            productRepository.add(product);
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
}