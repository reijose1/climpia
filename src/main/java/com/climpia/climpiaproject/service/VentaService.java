package com.climpia.climpiaproject.service;

import com.climpia.climpiaproject.entities.Venta;
import com.climpia.climpiaproject.entities.Detalle;
import com.climpia.climpiaproject.entities.Usuario;
import java.time.LocalDate;
import java.util.List;

public interface VentaService {
    void createSale(Venta venta, List<Detalle> detalles);
    Venta getSale(Integer id);
    void updateSale(Venta venta);
    void deleteSale(Integer id);
    List<Venta> getAllSales();
    List<Venta> getSalesByCustomer(Usuario cliente);
    List<Venta> getSalesByDate(LocalDate fecha);
    List<Venta> getSalesBySeller(String vendedor);
    List<Detalle> getSaleDetails(Integer ventaId);
}