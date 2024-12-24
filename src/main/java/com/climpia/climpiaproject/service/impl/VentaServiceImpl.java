package com.climpia.climpiaproject.service.impl;

import com.climpia.climpiaproject.dao.VentaDAO;
import com.climpia.climpiaproject.dao.DetalleDAO;
import com.climpia.climpiaproject.entities.Venta;
import com.climpia.climpiaproject.entities.Detalle;
import com.climpia.climpiaproject.entities.Usuario;
import com.climpia.climpiaproject.service.VentaService;
import com.climpia.climpiaproject.service.ProductoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class VentaServiceImpl implements VentaService {

    @Inject
    private VentaDAO ventaDAO;

    @Inject
    private DetalleDAO detalleDAO;

    @Inject
    private ProductoService productoService;

    @Override
    @Transactional
    public void createSale(Venta venta, List<Detalle> detalles) {
        // Validate stock availability
        for (Detalle detalle : detalles) {
            if (!productoService.isProductAvailable(detalle.getIdprod().getId(), detalle.getCant().intValue())) {
                throw new RuntimeException("Insufficient stock for product: " + detalle.getIdprod().getNombre());
            }
        }

        // Create sale
        ventaDAO.create(venta);

        // Create details and update stock
        for (Detalle detalle : detalles) {
            detalle.setIdventa(venta);
            detalleDAO.create(detalle);
            productoService.updateProductStock(detalle.getIdprod().getId(), -detalle.getCant().intValue());
        }
    }

    @Override
    public Venta getSale(Integer id) {
        return ventaDAO.read(id);
    }

    @Override
    @Transactional
    public void updateSale(Venta venta) {
        ventaDAO.update(venta);
    }

    @Override
    @Transactional
    public void deleteSale(Integer id) {
        // Restore stock before deleting
        List<Detalle> detalles = getSaleDetails(id);
        for (Detalle detalle : detalles) {
            productoService.updateProductStock(detalle.getIdprod().getId(), detalle.getCant().intValue());
            detalleDAO.delete(detalle.getId());
        }
        ventaDAO.delete(id);
    }

    @Override
    public List<Venta> getAllSales() {
        return ventaDAO.findAll();
    }

    @Override
    public List<Venta> getSalesByCustomer(Usuario cliente) {
        return ventaDAO.findByCliente(cliente);
    }

    @Override
    public List<Venta> getSalesByDate(LocalDate fecha) {
        return ventaDAO.findByFecha(fecha);
    }

    @Override
    public List<Venta> getSalesBySeller(String vendedor) {
        return ventaDAO.findByVendedor(vendedor);
    }

    @Override
    public List<Detalle> getSaleDetails(Integer ventaId) {
        Venta venta = getSale(ventaId);
        return venta != null ? detalleDAO.findByVenta(venta) : List.of();
    }
}