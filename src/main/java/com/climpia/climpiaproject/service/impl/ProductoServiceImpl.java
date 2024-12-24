package com.climpia.climpiaproject.service.impl;

import com.climpia.climpiaproject.dao.ProductoDAO;
import com.climpia.climpiaproject.entities.Producto;
import com.climpia.climpiaproject.service.ProductoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoDAO productoDAO;

    @Override
    @Transactional
    public void createProduct(Producto producto) {
        productoDAO.create(producto);
    }

    @Override
    public Producto getProduct(Integer id) {
        return productoDAO.read(id);
    }

    @Override
    @Transactional
    public void updateProduct(Producto producto) {
        productoDAO.update(producto);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        productoDAO.delete(id);
    }

    @Override
    public List<Producto> getAllProducts() {
        return productoDAO.findAll();
    }

    @Override
    public List<Producto> getProductsByCategory(Integer categoryId) {
        return productoDAO.findByCategoria(categoryId);
    }

    @Override
    public List<Producto> searchProductsByName(String name) {
        return productoDAO.findByNombre(name);
    }

    @Override
    @Transactional
    public void updateProductStock(Integer productId, Integer quantity) {
        productoDAO.updateStock(productId, quantity);
    }

    @Override
    public boolean isProductAvailable(Integer productId, Integer quantity) {
        Producto producto = getProduct(productId);
        return producto != null && producto.getStock() >= quantity;
    }
}