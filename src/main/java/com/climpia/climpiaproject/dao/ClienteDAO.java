package com.climpia.climpiaproject.dao;

import com.climpia.climpiaproject.entities.Cliente;
import java.util.List;

public interface ClienteDAO {
    void create(Cliente cliente);
    Cliente read(Integer id);
    void update(Cliente cliente);
    void delete(Integer id);
    List<Cliente> findAll();
    List<Cliente> findByNombre(String nombre);
    Cliente findByEmail(String email);
}