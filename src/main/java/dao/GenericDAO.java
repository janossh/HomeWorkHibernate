package dao;

import entity.Customer;

import java.util.List;

public interface GenericDAO {
    public void save(Customer t);

    public Customer getById(int id);

    public void update(Customer t);

    public void delete(Customer t);

    public List<Customer> getAll();
}
