package repository;

import entity.Basket;
import entity.Product;
import repository.exception.RepositoryException;

import java.util.UUID;

public interface BasketRepository {

    Basket get(UUID userId) throws RepositoryException;
    void addProduct(Basket basket, Product product) throws RepositoryException;
    void removeProduct(Basket basket, Product product) throws RepositoryException;
}