package com.mycompany.barbeitoyamil.temac;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ciudadanoTest {
    public static Ciudadano ciu;
    private static int i = 0;

    @BeforeClass
    public static void antesDeTodo() {
        ciu = new Ciudadano("Anonimus", 1212);
        System.out.println("INGRESO de ciudadano del bien");
    }

    @AfterClass
    public static void despuesDeTodo() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        System.out.println(ayer + " - Baches reparados, Ciudadanos Felices");
    }

    @Before
    public void setUp() {
        i++;
        System.out.println("----------------------------------------");
        System.out.println("Ejecutando Test Nro: " + i);
    }

    @After
    public void tearDown() {
        System.out.println("----------------------------------------");
    }

    @Test
    public void testValidarUsr() {
        System.out.println("Ejecutando testValidarUsr (Debe fallar)");
        int contra = 123456;
        boolean result = ciu.validarPassword(contra);
        assertTrue(result);
    }

    @Test
    public void testValidarEmail() {
        System.out.println("Ejecutando testValidarEmail (Debe tener éxito)");
        String email = "test@example.com";
        boolean valid = email.contains("@") && email.contains(".") && email.length() < 20;
        assertTrue(valid);
    }

    @Test
    public void TestCambioPass() {
        System.out.println("Ejecutando TestCambioPass (Debe tener éxito)");
        String newPass = "1234";
        ciu.cambioPassword(newPass);
        assertEquals(1234, ciu.getContrasenia());
        System.out.println("Contraseña cambiada exitosamente a: " + ciu.getContrasenia());
    }

    @Test(timeout = 30)
    public void testDelay() throws InterruptedException {
        System.out.println("Ejecutando testDelay (Debe causar timeout error)");
        ciu.delay(45);
    }

    @Test
    public void testCiudadanosDiferentes() {
        System.out.println("Ejecutando testCiudadanosDiferentes (Debe tener éxito)");
        Ciudadano c1 = new Ciudadano("Pedro", 1111);
        Ciudadano c2 = new Ciudadano("Juan", 2222);
        boolean dif = c1.ciudadanosDiferentes(c2);
        assertTrue(dif);
        assertNotSame("misma persona", c1, c2);
    }
}
