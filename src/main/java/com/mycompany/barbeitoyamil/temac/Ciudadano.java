package com.mycompany.barbeitoyamil.temac;

public class Ciudadano {
    private String nombre;
    private String email;
    private int contrasenia;

    // Constructor sin parámetros
    public Ciudadano() {
        this.nombre = "";
        this.email = "";
        this.contrasenia = 0;
    }

    // Constructor con nombre y contraseña (parte 2)
    public Ciudadano(String nombre, int contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = "";
    }

    // Constructor completo
    public Ciudadano(String nombre, String email, int contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    // Valida el ingreso comparando contraseñas
    public boolean validarIngreso(int psw) {
        return this.contrasenia == psw;
    }

    // Cambia la contraseña convirtiendo el String a entero
    public void cambioPassword(String newPass) {
        try {
            this.contrasenia = Integer.parseInt(newPass);
        } catch (NumberFormatException e) {
            System.out.println("Error: El nuevo password debe ser numérico.");
        }
    }

    // Duerme el hilo por la cantidad de milisegundos indicada
    public void delay(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            System.out.println("Delay de " + mili + " milisegundos");
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    // Compara si son ciudadanos diferentes (objetos diferentes por referencia)
    public boolean ciudadanosDiferentes(Ciudadano c2) {
        return this != c2;
    }

    // Getters y Setters
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
