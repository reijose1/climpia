package com.climpia.climpiaproject.dao.impl;

import com.climpia.climpiaproject.dao.DetalleDAO;
import com.climpia.climpiaproject.entities.Detalle;
import com.climpia.climpiaproject.entities.Venta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DetalleDAOImpl implements DetalleDAO {

    @PersistenceContext(unitName = "SalesPU")
    private EntityManager em;

    @Override
    @Transactional
    public void create(Detalle detalle) {
        em.persist(detalle);
    }

    @Override
    public Detalle read(Integer id) {
        return em.find(Detalle.class, id);
    }

    @Override
    @Transactional
    public void update(Detalle detalle) {
        em.merge(detalle);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Detalle detalle = read(id);
        if (detalle != null) {
            em.remove(detalle);
        }
    }

    @Override
    public List<Detalle> findAll() {
        TypedQuery<Detalle> query = em.createQuery("SELECT d FROM Detalle d", Detalle.class);
        return query.getResultList();
    }

    @Override
    public List<Detalle> findByVenta(Venta venta) {
        TypedQuery<Detalle> query = em.createQuery(
            "SELECT d FROM Detalle d WHERE d.idventa = :venta", Detalle.class);
        query.setParameter("venta", venta);
        return query.getResultList();
    }
}