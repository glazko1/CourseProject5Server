package repository;

import entity.Order;
import repository.exception.RepositoryException;

import java.util.List;

public interface OrderRepository {

    List<Order> getAll() throws RepositoryException;
    Order get(int orderId) throws RepositoryException;
    List<Order> getByUserId(int userId) throws RepositoryException;
    void add(Order order) throws RepositoryException;
    void update(Order order) throws RepositoryException;
}