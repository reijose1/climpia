package com.climpia.climpiaproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @Column(name = "idpago", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idventa", nullable = false)
    private com.climpia.climpiaproject.entities.Venta idventa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmedio", nullable = false)
    private MedioPago idmedio;

    @Size(max = 100)
    @NotNull
    @Column(name = "detalle", nullable = false, length = 100)
    private String detalle;

    @NotNull
    @Column(name = "importe", nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;

    @Size(max = 1000)
    @NotNull
    @Column(name = "observaciones", nullable = false, length = 1000)
    private String observaciones;

    public Pago() {
    }

    public Pago(Integer id, com.climpia.climpiaproject.entities.Venta idventa, MedioPago idmedio, String detalle, BigDecimal importe, String observaciones) {
        this.id = id;
        this.idventa = idventa;
        this.idmedio = idmedio;
        this.detalle = detalle;
        this.importe = importe;
        this.observaciones = observaciones;
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

    public MedioPago getIdmedio() {
        return idmedio;
    }

    public void setIdmedio(MedioPago idmedio) {
        this.idmedio = idmedio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}