package repository;

import entity.Basket;
import entity.Product;
import repository.exception.RepositoryException;

public interface BasketRepository {

    Basket get(int userId) throws RepositoryException;
    void addProduct(Basket basket, Product product) throws RepositoryException;
    void removeProduct(Basket basket, Product product) throws RepositoryException;
    void clear(Basket basket) throws RepositoryException;
}