package com.mycompany.barbeitoyamil.temac;

import java.time.LocalDate;

public class PedidoObra {
    private int numeroPedido;
    private String ubicacion;
    private double tamanio;
    private LocalDate fechaCreacion;
    private LocalDate fechaReparacion; // null initially
    private String observaciones;
    private Brigada brigada; // null initially

    public PedidoObra(int numeroPedido, String ubicacion, double tamanio, LocalDate fechaCreacion) {
        this.numeroPedido = numeroPedido;
        this.ubicacion = ubicacion;
        this.tamanio = tamanio;
        this.fechaCreacion = fechaCreacion;
        this.fechaReparacion = null;
        this.observaciones = "";
        this.brigada = null;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getTamanio() {
        return tamanio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(LocalDate fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Brigada getBrigada() {
        return brigada;
    }

    public void setBrigada(Brigada brigada) {
        this.brigada = brigada;
    }
}
