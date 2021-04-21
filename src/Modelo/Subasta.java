/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author lalem
 */
public class Subasta {
    private int id;
    private Date fechaInicio;
    private boolean vendido;
    private Date fechaLimite;
    private int iditem;
    private float precioInicial;
    private float precioFinal;
    private String categoría;
    private String subcategoria;
    private String descripcion;

    public Subasta(int id,int idItem, Date fechaInicio,  Date fechaLimite, boolean vendido, float precioInicial,float precioFinal, String categoría, String subcategoria, String descripcion) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.vendido = vendido;
        this.fechaLimite = fechaLimite;
        this.iditem = iditem;
        this.precioInicial = precioInicial;
        this.precioFinal = precioFinal;
        this.categoría = categoría;
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public float getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(float precioInicial) {
        this.precioInicial = precioInicial;
    }

    public String getCategoría() {
        return categoría;
    }

    public void setCategoría(String categoría) {
        this.categoría = categoría;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
