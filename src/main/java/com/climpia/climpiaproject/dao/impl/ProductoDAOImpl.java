package com.climpia.climpiaproject.dao.impl;

import com.climpia.climpiaproject.dao.ProductoDAO;
import com.climpia.climpiaproject.entities.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductoDAOImpl implements ProductoDAO {

    @PersistenceContext(unitName = "SalesPU")
    private EntityManager em;

    @Override
    @Transactional
    public void create(Producto producto) {
        em.persist(producto);
    }

    @Override
    public Producto read(Integer id) {
        return em.find(Producto.class, id);
    }

    @Override
    @Transactional
    public void update(Producto producto) {
        em.merge(producto);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Producto producto = read(id);
        if (producto != null) {
            em.remove(producto);
        }
    }

    @Override
    public List<Producto> findAll() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    @Override
    public List<Producto> findByCategoria(Integer categoriaId) {
        TypedQuery<Producto> query = em.createQuery(
            "SELECT p FROM Producto p WHERE p.idcat.id = :categoriaId", Producto.class);
        query.setParameter("categoriaId", categoriaId);
        return query.getResultList();
    }

    @Override
    public List<Producto> findByNombre(String nombre) {
        TypedQuery<Producto> query = em.createQuery(
            "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(:nombre)", Producto.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateStock(Integer id, Integer quantity) {
        Producto producto = read(id);
        if (producto != null) {
            producto.setStock(producto.getStock() + quantity);
            update(producto);
        }
    }
}