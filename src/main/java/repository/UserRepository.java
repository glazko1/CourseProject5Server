package repository;

import entity.User;
import repository.exception.RepositoryException;

import java.util.List;

public interface UserRepository {

    List<User> getAll() throws RepositoryException;
    User get(String username, String password) throws RepositoryException;
    User get(int userId) throws RepositoryException;
    User getByUsername(String username) throws RepositoryException;
    User getByEmail(String email) throws RepositoryException;
    void add(User user) throws RepositoryException;
    void update(User user) throws RepositoryException;
}