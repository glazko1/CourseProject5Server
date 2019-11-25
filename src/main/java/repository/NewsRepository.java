package repository;

import entity.News;
import repository.exception.RepositoryException;

import java.util.List;

public interface NewsRepository {

    List<News> getAll() throws RepositoryException;
}