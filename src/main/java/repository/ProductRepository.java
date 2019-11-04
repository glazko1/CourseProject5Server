package repository;

import entity.Product;
import repository.exception.RepositoryException;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

    Product get(int productId) throws RepositoryException;
    List<Product> getAll() throws RepositoryException;
}