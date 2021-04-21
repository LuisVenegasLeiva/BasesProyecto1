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
public class Puja {
    int id;
    int idsubasta;
    String comprador;
    Double monto;
    Date fecha;

    public Puja(int id, int idsubasta, String comprador, Double monto, Date fecha) {
        this.id = id;
        this.idsubasta = idsubasta;
        this.comprador = comprador;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdsubasta() {
        return idsubasta;
    }

    public void setIdsubasta(int idsubasta) {
        this.idsubasta = idsubasta;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
