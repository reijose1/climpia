package com.climpia.climpiaproject.converter;

import com.climpia.climpiaproject.entities.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CategoriaConverter implements Converter {
    
    @PersistenceContext(unitName = "SalesPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return em.find(Categoria.class, Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Categoria) value).getId().toString();
    }
}