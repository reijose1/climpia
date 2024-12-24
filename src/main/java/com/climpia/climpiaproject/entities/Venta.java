package com.climpia.climpiaproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @Column(name = "idventa", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcli", nullable = false)
    private Usuario idcli;

    @Size(max = 100)
    @NotNull
    @Column(name = "vendedor", nullable = false, length = 100)
    private String vendedor;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @Column(name = "importe", nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;

    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
    }

    public Venta() {
    }

    public Venta(Integer id, Usuario idcli, String vendedor, LocalDate fecha, BigDecimal importe) {
        this.id = id;
        this.idcli = idcli;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.importe = importe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdcli() {
        return idcli;
    }

    public void setIdcli(Usuario idcli) {
        this.idcli = idcli;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}