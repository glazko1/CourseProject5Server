package service;

import entity.Order;
import entity.User;
import service.exception.ServiceException;

import java.util.List;

public interface AdminService {

    /**
     * Work with products
     */
    void addProduct(String productName, int departmentId,
                    double price, int amount) throws ServiceException;
    void editProduct(int productId, String productName, int departmentId,
                     double price, int amount) throws ServiceException;
    void deleteProduct(int productId) throws ServiceException;

    /**
     * Work with orders
     */
    List<Order> getAllOrders() throws ServiceException;
    void processOrder(int orderId) throws ServiceException;

    /**
     * Work with users
     */
    List<User> getAllUsers() throws ServiceException;
    void changeBanStatus(int userId) throws ServiceException;
    void changeUserStatus(int userId, int statusId) throws ServiceException;
}