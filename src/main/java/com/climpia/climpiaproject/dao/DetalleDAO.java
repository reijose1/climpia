package com.climpia.climpiaproject.dao;

import com.climpia.climpiaproject.entities.Detalle;
import com.climpia.climpiaproject.entities.Venta;
import java.util.List;

public interface DetalleDAO {
    void create(Detalle detalle);
    Detalle read(Integer id);
    void update(Detalle detalle);
    void delete(Integer id);
    List<Detalle> findAll();
    List<Detalle> findByVenta(Venta venta);
}