package com.climpia.climpiaproject.converter;

import com.climpia.climpiaproject.entities.Cliente;
import com.climpia.climpiaproject.service.ClienteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class ClienteConverter implements Converter<Cliente> {
    
    @Inject
    private ClienteService clienteService;

    @Override
    public Cliente getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return clienteService.getCustomer(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Cliente value) {
        if (value == null) {
            return "";
        }
        return value.getId().toString();
    }
}