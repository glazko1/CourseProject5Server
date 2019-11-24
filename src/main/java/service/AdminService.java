package service;

import entity.Order;
import service.exception.ServiceException;

import java.util.List;

public interface AdminService {

    void addProduct(String productName, int departmentId,
                    double price, int amount) throws ServiceException;
    List<Order> getAllOrders() throws ServiceException;
    void processOrder(int orderId) throws ServiceException;
}