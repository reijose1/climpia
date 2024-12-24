package com.climpia.climpiaproject.converter;

import com.climpia.climpiaproject.entities.Producto;
import com.climpia.climpiaproject.service.ProductoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class ProductoConverter implements Converter<Producto> {
    
    @Inject
    private ProductoService productoService;

    @Override
    public Producto getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return productoService.getProduct(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Producto value) {
        if (value == null) {
            return "";
        }
        return value.getId().toString();
    }
}