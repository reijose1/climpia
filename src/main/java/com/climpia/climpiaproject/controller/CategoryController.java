package com.climpia.climpiaproject.controller;

import com.climpia.climpiaproject.entities.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryController {
    
    @PersistenceContext(unitName = "SalesPU")
    private EntityManager em;
    
    private List<Categoria> categories;
    
    public List<Categoria> getCategories() {
        if (categories == null) {
            categories = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        }
        return categories;
    }
    
    public void setCategories(List<Categoria> categories) {
        this.categories = categories;
    }
    
    public void refreshCategories() {
        categories = null;
        getCategories();
    }
}