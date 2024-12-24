package com.climpia.climpiaproject.service;

import com.climpia.climpiaproject.entities.Cliente;
import java.util.List;

public interface ClienteService {
    void createCustomer(Cliente cliente);
    Cliente getCustomer(Integer id);
    void updateCustomer(Cliente cliente);
    void deleteCustomer(Integer id);
    List<Cliente> getAllCustomers();
    List<Cliente> searchCustomersByName(String name);
    Cliente findCustomerByEmail(String email);
}