package service;

import entity.Basket;
import entity.Department;
import entity.News;
import entity.Order;
import entity.Product;
import entity.User;
import service.exception.ServiceException;

import java.util.List;

public interface UserService {

    /**
     * Work with users
     */
    User signIn(String username, String password) throws ServiceException;
    void signUp(String username, String firstName, String lastName,
                String email, String password, int avatarNumber) throws ServiceException;
    User getUser(int userId) throws ServiceException;
    User getUserByEmail(String email) throws ServiceException;
    void changeAvatar(int userId, int avatarNumber) throws ServiceException;
    void changeEmail(int userId, String newEmail) throws ServiceException;
    void changePassword(int userId, String currentPassword, String newPassword) throws ServiceException;
    void restorePassword(int userId, String password) throws ServiceException;

    /**
     * Work with products, departments and news
     */
    List<Product> getAllProducts() throws ServiceException;
    List<Department> getAllDepartments() throws ServiceException;
    List<News> getAllNews() throws ServiceException;

    /**
     * Work with baskets
     */
    Basket getBasket(int userId) throws ServiceException;
    void addProductToBasket(int userId, int productId) throws ServiceException;
    void removeProductFromBasket(int userId, int productId) throws ServiceException;

    /**
     * Work with orders
     */
    void addOrder(int userId, String region, String locality,
                  String street, int houseNumber, int flatNumber) throws ServiceException;
    List<Order> getOrders(int userId) throws ServiceException;
    void cancelOrder(int orderId) throws ServiceException;
}