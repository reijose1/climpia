package com.climpia.climpiaproject.dao.impl;

import com.climpia.climpiaproject.dao.VentaDAO;
import com.climpia.climpiaproject.entities.Venta;
import com.climpia.climpiaproject.entities.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class VentaDAOImpl implements VentaDAO {

    @PersistenceContext(unitName = "SalesPU")
    private EntityManager em;

    @Override
    @Transactional
    public void create(Venta venta) {
        em.persist(venta);
    }

    @Override
    public Venta read(Integer id) {
        return em.find(Venta.class, id);
    }

    @Override
    @Transactional
    public void update(Venta venta) {
        em.merge(venta);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Venta venta = read(id);
        if (venta != null) {
            em.remove(venta);
        }
    }

    @Override
    public List<Venta> findAll() {
        TypedQuery<Venta> query = em.createQuery("SELECT v FROM Venta v", Venta.class);
        return query.getResultList();
    }

    @Override
    public List<Venta> findByCliente(Usuario cliente) {
        TypedQuery<Venta> query = em.createQuery(
            "SELECT v FROM Venta v WHERE v.idcli = :cliente", Venta.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }

    @Override
    public List<Venta> findByFecha(LocalDate fecha) {
        TypedQuery<Venta> query = em.createQuery(
            "SELECT v FROM Venta v WHERE v.fecha = :fecha", Venta.class);
        query.setParameter("fecha", fecha);
        return query.getResultList();
    }

    @Override
    public List<Venta> findByVendedor(String vendedor) {
        TypedQuery<Venta> query = em.createQuery(
            "SELECT v FROM Venta v WHERE v.vendedor = :vendedor", Venta.class);
        query.setParameter("vendedor", vendedor);
        return query.getResultList();
    }
}