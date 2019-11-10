package repository;

import entity.Product;
import repository.exception.RepositoryException;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll() throws RepositoryException;
    Product get(int productId) throws RepositoryException;
    void add(Product product) throws RepositoryException;
}