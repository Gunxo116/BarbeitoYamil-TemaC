package com.mycompany.barbeitoyamil.temac;

import java.time.LocalDate;

public class InformeRotura {
    private String codInf;
    private LocalDate fechaInforme;
    private String problema;
    private int codigoUrgencia;
    private Bache bache;
    private Ciudadano ciudadano;
    private PedidoObra pedidoReparacion;

    public InformeRotura(String codInf, LocalDate fechaInforme, String problema, int codigoUrgencia, Bache bache, Ciudadano ciudadano) {
        this.codInf = codInf;
        this.fechaInforme = fechaInforme;
        this.problema = problema;
        this.codigoUrgencia = codigoUrgencia;
        this.bache = bache;
        this.ciudadano = ciudadano;
        this.pedidoReparacion = null;
    }

    public String getCodInf() {
        return codInf;
    }

    public LocalDate getFechaInforme() {
        return fechaInforme;
    }

    public String getProblema() {
        return problema;
    }

    public int getCodigoUrgencia() {
        return codigoUrgencia;
    }

    public Bache getBache() {
        return bache;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public PedidoObra getPedidoReparacion() {
        return pedidoReparacion;
    }

    public void setPedidoReparacion(PedidoObra pedidoReparacion) {
        this.pedidoReparacion = pedidoReparacion;
    }
}
