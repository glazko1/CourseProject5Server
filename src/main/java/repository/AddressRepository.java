package repository;

import entity.Address;
import repository.exception.RepositoryException;

public interface AddressRepository {

    Address get(int addressId) throws RepositoryException;
    Address get(String region, String locality, String street,
                int houseNumber, int flatNumber) throws RepositoryException;
    void add(Address address) throws RepositoryException;
}