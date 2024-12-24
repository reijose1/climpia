package com.climpia.climpiaproject.controller;

import com.climpia.climpiaproject.entities.Cliente;
import com.climpia.climpiaproject.service.ClienteService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ClienteController implements Serializable {
    
    @Inject
    private ClienteService clienteService;
    
    private List<Cliente> clientes;
    private Cliente selectedCliente;
    private String searchTerm;
    
    @PostConstruct
    public void init() {
        clientes = clienteService.getAllCustomers();
        selectedCliente = new Cliente();
    }
    
    public void searchClientes() {
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            clientes = clienteService.searchCustomersByName(searchTerm);
        } else {
            clientes = clienteService.getAllCustomers();
        }
    }
    
    public void saveCliente() {
        try {
            if (selectedCliente.getId() == null) {
                // Check if email is already registered
                Cliente existingCliente = clienteService.findCustomerByEmail(selectedCliente.getEmail());
                if (existingCliente != null) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email already registered"));
                    return;
                }
                
                clienteService.createCustomer(selectedCliente);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Customer created successfully"));
            } else {
                clienteService.updateCustomer(selectedCliente);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Customer updated successfully"));
            }
            clientes = clienteService.getAllCustomers();
            selectedCliente = new Cliente();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error processing customer"));
        }
    }
    
    public void deleteCliente(Integer id) {
        try {
            clienteService.deleteCustomer(id);
            clientes = clienteService.getAllCustomers();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Customer deleted successfully"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error deleting customer"));
        }
    }
    
    public void prepareNewCliente() {
        selectedCliente = new Cliente();
    }
    
    public void editCliente(Cliente cliente) {
        selectedCliente = cliente;
    }

    // Getters and Setters
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}