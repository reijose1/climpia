package com.climpia.climpiaproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "idcli", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcli", nullable = false)
    private Cliente cliente;

    @Size(max = 30)
    @NotNull
    @Column(name = "usuario", nullable = false, length = 30)
    private String usuario;

    @Size(max = 40)
    @NotNull
    @Column(name = "clave", nullable = false, length = 40)
    private String clave;

    @NotNull
    @Column(name = "estado", nullable = false, precision = 2)
    private BigDecimal estado;

    public Usuario() {
    }

    public Usuario(Integer id, String usuario, String clave, BigDecimal estado) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public BigDecimal getEstado() {
        return estado;
    }

    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }

}