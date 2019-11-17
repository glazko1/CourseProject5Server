package repository;

import entity.User;
import repository.exception.RepositoryException;

import java.util.List;

public interface UserRepository {

    List<User> getAll() throws RepositoryException;
    User get(String username, String password) throws RepositoryException;
    User get(int userId) throws RepositoryException;
    void add(User user) throws RepositoryException;
}