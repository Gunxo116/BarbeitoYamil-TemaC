package com.mycompany.barbeitoyamil.temac;

public class Ciudadano {
    private String nombre;
    private String email;
    private int contrasenia;

    public Ciudadano() {
        this.nombre = "";
        this.email = "";
        this.contrasenia = 0;
    }

    public Ciudadano(String nombre, int contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = "";
    }

    public Ciudadano(String nombre, String email, int contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public boolean validarIngreso(int psw) {
        return this.contrasenia == psw;
    }

    public boolean validarPassword(int psw) {
        return validarIngreso(psw);
    }

    public void cambioPassword(String newPass) {
        try {
            this.contrasenia = Integer.parseInt(newPass);
        } catch (NumberFormatException e) {
            System.out.println("Error: El nuevo password debe ser numérico.");
        }
    }

    public void delay(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            System.out.println("Delay de " + mili + " milisegundos");
            Thread.currentThread().interrupt();
        }
    }

    public boolean ciudadanosDiferentes(Ciudadano c2) {
        return this != c2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContrasenia() {
        return contrasenia;
    }
}
