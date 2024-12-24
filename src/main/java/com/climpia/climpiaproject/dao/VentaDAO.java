package com.climpia.climpiaproject.dao;

import com.climpia.climpiaproject.entities.Venta;
import com.climpia.climpiaproject.entities.Usuario;
import java.time.LocalDate;
import java.util.List;

public interface VentaDAO {
    void create(Venta venta);
    Venta read(Integer id);
    void update(Venta venta);
    void delete(Integer id);
    List<Venta> findAll();
    List<Venta> findByCliente(Usuario cliente);
    List<Venta> findByFecha(LocalDate fecha);
    List<Venta> findByVendedor(String vendedor);
}