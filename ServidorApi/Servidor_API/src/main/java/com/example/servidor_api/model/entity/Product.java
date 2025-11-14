package com.example.servidor_api.model.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity

@Table(name = "Products")
public class Product {

    @Id

    @Column(name = "ProductID")
    private Integer id;

    @Column(name = "ProductName")
    private String nombre;

    @Column(name = "SupplierID")
    private Integer proveedorId;

    @Column(name = "CategoryID")
    private Integer categoriaId;

    @Column(name = "Unit")
    private String unidad;

    @Column(name = "Price")
    private BigDecimal precio;

    public Product() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}