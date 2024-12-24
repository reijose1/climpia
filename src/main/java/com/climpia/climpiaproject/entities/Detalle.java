package com.climpia.climpiaproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle")
public class Detalle {
    @Id
    @Column(name = "iddetalle", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idventa", nullable = false)
    private com.climpia.climpiaproject.entities.Venta idventa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idprod", nullable = false)
    private com.climpia.climpiaproject.entities.Producto idprod;

    @NotNull
    @Column(name = "cant", nullable = false, precision = 10)
    private BigDecimal cant;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    public Detalle() {
    }

    public Detalle(Integer id, com.climpia.climpiaproject.entities.Venta idventa, com.climpia.climpiaproject.entities.Producto idprod, BigDecimal cant, BigDecimal precio, BigDecimal subtotal) {
        this.id = id;
        this.idventa = idventa;
        this.idprod = idprod;
        this.cant = cant;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.climpia.climpiaproject.entities.Venta getIdventa() {
        return idventa;
    }

    public void setIdventa(com.climpia.climpiaproject.entities.Venta idventa) {
        this.idventa = idventa;
    }

    public com.climpia.climpiaproject.entities.Producto getIdprod() {
        return idprod;
    }

    public void setIdprod(com.climpia.climpiaproject.entities.Producto idprod) {
        this.idprod = idprod;
    }

    public BigDecimal getCant() {
        return cant;
    }

    public void setCant(BigDecimal cant) {
        this.cant = cant;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}