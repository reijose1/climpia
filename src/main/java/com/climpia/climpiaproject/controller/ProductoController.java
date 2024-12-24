package com.climpia.climpiaproject.controller;

import com.climpia.climpiaproject.entities.Producto;
import com.climpia.climpiaproject.entities.Categoria;
import com.climpia.climpiaproject.service.ProductoService;
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
public class ProductoController implements Serializable {
    
    @Inject
    private ProductoService productoService;
    
    private List<Producto> productos;
    private Producto selectedProduct;
    private String searchTerm;
    
    @PostConstruct
    public void init() {
        productos = productoService.getAllProducts();
        selectedProduct = new Producto();
    }
    
    public void searchProducts() {
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            productos = productoService.searchProductsByName(searchTerm);
        } else {
            productos = productoService.getAllProducts();
        }
    }
    
    public void saveProduct() {
        try {
            if (selectedProduct.getId() == null) {
                productoService.createProduct(selectedProduct);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product created successfully"));
            } else {
                productoService.updateProduct(selectedProduct);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product updated successfully"));
            }
            productos = productoService.getAllProducts();
            selectedProduct = new Producto();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error processing product"));
        }
    }
    
    public void deleteProduct(Integer id) {
        try {
            productoService.deleteProduct(id);
            productos = productoService.getAllProducts();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product deleted successfully"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error deleting product"));
        }
    }
    
    public void prepareNewProduct() {
        selectedProduct = new Producto();
    }
    
    public void editProduct(Producto product) {
        selectedProduct = product;
    }

    // Getters and Setters
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Producto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}