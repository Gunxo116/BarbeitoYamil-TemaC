package com.mycompany.barbeitoyamil.temac;

public class Bache {
    private int id;
    private double tamanio;
    private String calleA;
    private String calleB;
    private int altura;
    private String barrio;
    private int prioridad; // 1 a 5 (menos grave)
    private String estado; // "sin reparar", "reparado"

    public Bache(int id, double tamanio, String calleA, String calleB, int altura, String barrio, int prioridad) {
        this.id = id;
        this.tamanio = tamanio;
        this.calleA = calleA;
        this.calleB = calleB;
        this.altura = altura;
        this.barrio = barrio;
        this.prioridad = prioridad;
        this.estado = "sin reparar";
    }

    public String getValores() {
        return "Bache ID: " + id + ", Ubicación: " + calleA + " y " + calleB + " Nro " + altura + ", Barrio: " + barrio + ", Prioridad: " + prioridad + ", Estado: " + estado;
    }

    public boolean isReparado() {
        return "reparado".equalsIgnoreCase(this.estado);
    }

    public int getId() {
        return id;
    }

    public double getTamanio() {
        return tamanio;
    }

    public String getCalleA() {
        return calleA;
    }

    public String getCalleB() {
        return calleB;
    }

    public int getAltura() {
        return altura;
    }

    public String getBarrio() {
        return barrio;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
