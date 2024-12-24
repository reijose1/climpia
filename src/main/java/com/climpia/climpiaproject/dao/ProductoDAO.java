package com.climpia.climpiaproject.dao;

import com.climpia.climpiaproject.entities.Producto;
import java.util.List;

public interface ProductoDAO {
    void create(Producto producto);
    Producto read(Integer id);
    void update(Producto producto);
    void delete(Integer id);
    List<Producto> findAll();
    List<Producto> findByCategoria(Integer categoriaId);
    List<Producto> findByNombre(String nombre);
    void updateStock(Integer id, Integer quantity);
}