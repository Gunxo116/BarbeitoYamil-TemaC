package com.mycompany.barbeitoyamil.temac;

import java.util.List;

public class Brigada {
    private int numero;
    private String nombre; // Alfa, Delta, Bravo, RicoChet
    private int cantidadTrabajadores;
    private String equipamiento;
    private String materiales;
    private Trabajador jefe;
    private List<Trabajador> trabajadores;

    public Brigada(int numero, String nombre, String equipamiento, String materiales, Trabajador jefe, List<Trabajador> trabajadores) {
        this.numero = numero;
        this.nombre = nombre;
        this.equipamiento = equipamiento;
        this.materiales = materiales;
        this.jefe = jefe;
        this.trabajadores = trabajadores;
        // Total de trabajadores en la brigada = 3 obreros + 1 jefe
        this.cantidadTrabajadores = trabajadores.size() + (jefe != null ? 1 : 0);
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadTrabajadores() {
        return cantidadTrabajadores;
    }

    public String getEquipamiento() {
        return equipamiento;
    }

    public String getMateriales() {
        return materiales;
    }

    public Trabajador getJefe() {
        return jefe;
    }

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }
}
