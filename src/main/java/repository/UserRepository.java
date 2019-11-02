package repository;

import entity.User;
import repository.exception.RepositoryException;

import java.util.List;

public interface UserRepository {

    List<User> getAll() throws RepositoryException;
    User getUser(String username, String password) throws RepositoryException;
    void addUser(User user) throws RepositoryException;
}