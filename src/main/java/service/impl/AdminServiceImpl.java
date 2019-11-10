package service.impl;

import entity.Department;
import entity.Product;
import repository.DepartmentRepository;
import repository.ProductRepository;
import repository.exception.RepositoryException;
import repository.impl.DepartmentJpaRepository;
import repository.impl.ProductJpaRepository;
import service.AdminService;
import service.exception.ServiceException;

public class AdminServiceImpl implements AdminService {

    private static final AdminServiceImpl INSTANCE = new AdminServiceImpl();

    public static AdminServiceImpl getInstance() {
        return INSTANCE;
    }

    private AdminServiceImpl() {}

    private ProductRepository productRepository = ProductJpaRepository.getInstance();
    private DepartmentRepository departmentRepository = DepartmentJpaRepository.getInstance();

    @Override
    public void addProduct(String productName, int departmentId,
                           double price) throws ServiceException {
        try {
            Department department = departmentRepository.get(departmentId);
            String imagePath = "file:/E:/Java/CourseProject5/Server/src/main/resources/img/" + productName + ".jpg";
            Product product = new Product(productName, department, imagePath, price);
            productRepository.add(product);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}