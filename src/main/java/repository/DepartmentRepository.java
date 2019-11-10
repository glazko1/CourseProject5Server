package repository;

import entity.Department;
import repository.exception.RepositoryException;

public interface DepartmentRepository {

    Department get(int departmentId) throws RepositoryException;
}