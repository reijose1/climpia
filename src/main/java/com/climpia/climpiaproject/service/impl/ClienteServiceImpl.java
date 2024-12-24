package com.climpia.climpiaproject.service.impl;

import com.climpia.climpiaproject.dao.ClienteDAO;
import com.climpia.climpiaproject.entities.Cliente;
import com.climpia.climpiaproject.service.ClienteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    private ClienteDAO clienteDAO;

    @Override
    @Transactional
    public void createCustomer(Cliente cliente) {
        clienteDAO.create(cliente);
    }

    @Override
    public Cliente getCustomer(Integer id) {
        return clienteDAO.read(id);
    }

    @Override
    @Transactional
    public void updateCustomer(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    @Override
    @Transactional
    public void deleteCustomer(Integer id) {
        clienteDAO.delete(id);
    }

    @Override
    public List<Cliente> getAllCustomers() {
        return clienteDAO.findAll();
    }

    @Override
    public List<Cliente> searchCustomersByName(String name) {
        return clienteDAO.findByNombre(name);
    }

    @Override
    public Cliente findCustomerByEmail(String email) {
        return clienteDAO.findByEmail(email);
    }
}