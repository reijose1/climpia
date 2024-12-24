package com.climpia.climpiaproject.controller;

import com.climpia.climpiaproject.entities.Venta;
import com.climpia.climpiaproject.entities.Detalle;
import com.climpia.climpiaproject.entities.Producto;
import com.climpia.climpiaproject.entities.Usuario;
import com.climpia.climpiaproject.service.VentaService;
import com.climpia.climpiaproject.service.ProductoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class VentaController implements Serializable {
    
    @Inject
    private VentaService ventaService;
    
    @Inject
    private ProductoService productoService;
    
    private List<Venta> ventas;
    private Venta selectedVenta;
    private List<Detalle> detalles;
    private Detalle newDetalle;
    private LocalDate searchDate;
    private List<Producto> productos;
    
    @PostConstruct
    public void init() {
        ventas = ventaService.getAllSales();
        selectedVenta = new Venta();
        detalles = new ArrayList<>();
        newDetalle = new Detalle();
        productos = productoService.getAllProducts();
    }
    
    public void prepareNewSale() {
        selectedVenta = new Venta();
        selectedVenta.setFecha(LocalDate.now());
        detalles = new ArrayList<>();
        newDetalle = new Detalle();
    }
    
    public void addDetail() {
        if (newDetalle.getIdprod() != null && newDetalle.getCant().compareTo(BigDecimal.ZERO) > 0) {
            Producto producto = productoService.getProduct(newDetalle.getIdprod().getId());
            if (productoService.isProductAvailable(producto.getId(), newDetalle.getCant().intValue())) {
                newDetalle.setPrecio(producto.getPrecio());
                newDetalle.setSubtotal(newDetalle.getPrecio().multiply(newDetalle.getCant()));
                detalles.add(newDetalle);
                updateTotal();
                newDetalle = new Detalle();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Insufficient stock"));
            }
        }
    }
    
    public void removeDetail(Detalle detalle) {
        detalles.remove(detalle);
        updateTotal();
    }
    
    private void updateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Detalle detalle : detalles) {
            total = total.add(detalle.getSubtotal());
        }
        selectedVenta.setImporte(total);
    }
    
    public void saveSale() {
        try {
            if (detalles.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Add at least one product"));
                return;
            }
            
            ventaService.createSale(selectedVenta, detalles);
            ventas = ventaService.getAllSales();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Sale created successfully"));
            prepareNewSale();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    
    public void searchByDate() {
        if (searchDate != null) {
            ventas = ventaService.getSalesByDate(searchDate);
        } else {
            ventas = ventaService.getAllSales();
        }
    }
    
    public void viewDetails(Integer ventaId) {
        detalles = ventaService.getSaleDetails(ventaId);
    }

    // Getters and Setters
    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Venta getSelectedVenta() {
        return selectedVenta;
    }

    public void setSelectedVenta(Venta selectedVenta) {
        this.selectedVenta = selectedVenta;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public Detalle getNewDetalle() {
        return newDetalle;
    }

    public void setNewDetalle(Detalle newDetalle) {
        this.newDetalle = newDetalle;
    }

    public LocalDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        this.searchDate = searchDate;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}