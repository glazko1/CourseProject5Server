package repository;

import entity.Department;
import repository.exception.RepositoryException;

import java.util.List;

public interface DepartmentRepository {

    List<Department> getAll() throws RepositoryException;
    Department get(int departmentId) throws RepositoryException;
}