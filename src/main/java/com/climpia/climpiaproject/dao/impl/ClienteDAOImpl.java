package com.climpia.climpiaproject.dao.impl;

import com.climpia.climpiaproject.dao.ClienteDAO;
import com.climpia.climpiaproject.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext(unitName = "SalesPU")
    private EntityManager em;

    @Override
    @Transactional
    public void create(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public Cliente read(Integer id) {
        return em.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void update(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Cliente cliente = read(id);
        if (cliente != null) {
            em.remove(cliente);
        }
    }

    @Override
    public List<Cliente> findAll() {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        TypedQuery<Cliente> query = em.createQuery(
            "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)", Cliente.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    @Override
    public Cliente findByEmail(String email) {
        TypedQuery<Cliente> query = em.createQuery(
            "SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class);
        query.setParameter("email", email);
        List<Cliente> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}