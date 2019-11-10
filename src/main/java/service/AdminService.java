package service;

import service.exception.ServiceException;

public interface AdminService {

    void addProduct(String productName, int departmentId,
                    double price) throws ServiceException;
}