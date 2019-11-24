package service;

import entity.*;
import service.exception.ServiceException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User signIn(String username, String password) throws ServiceException;
    void signUp(String username, String firstName, String lastName,
                String email, String password, int avatarNumber) throws ServiceException;
    List<Product> getAllProducts() throws ServiceException;
    Basket getBasket(int userId) throws ServiceException;
    void addProductToBasket(int userId, int productId) throws ServiceException;
    void removeProductFromBasket(int userId, int productId) throws ServiceException;
    List<Department> getAllDepartments() throws ServiceException;
    void addOrder(int userId, String region, String locality,
                  String street, int houseNumber, int flatNumber) throws ServiceException;
    List<Order> getOrders(int userId) throws ServiceException;
    User getUser(int userId) throws ServiceException;
}