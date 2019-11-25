package repository;

import entity.UserStatus;
import repository.exception.RepositoryException;

public interface UserStatusRepository {

    UserStatus get(int userStatusId) throws RepositoryException;
}