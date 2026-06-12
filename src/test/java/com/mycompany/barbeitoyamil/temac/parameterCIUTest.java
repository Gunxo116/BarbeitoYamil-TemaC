package com.mycompany.barbeitoyamil.temac;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class parameterCIUTest {

    private String email;
    private boolean resultadoEsperado;
    private String descripcion;

    public parameterCIUTest(String email, boolean resultadoEsperado, String descripcion) {
        this.email = email;
        this.resultadoEsperado = resultadoEsperado;
        this.descripcion = descripcion;
    }

    public static boolean ValidarMail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    @Parameters
    public static Collection<Object[]> tomarDatos() {
        return Arrays.asList(new Object[][] {
            { "yamil.barbeito@gmail.com", true, "Email estándar válido" },
            { "usuario-123@sub.domain.org", true, "Email con guiones y subdominio" },
            { "correo_invalido@com", false, "Email sin dominio de nivel superior válido" },
            { "sin-arroba.dominio.com", false, "Email sin símbolo arroba" },
            { "@sinusuario.com", false, "Email sin nombre de usuario" }
        });
    }

    @Test
    public void testValidarEmail() {
        System.out.println("Validando email: " + email + " (" + descripcion + ")");
        boolean resultadoObtenido = ValidarMail(email);
        assertEquals(descripcion, resultadoEsperado, resultadoObtenido);
    }
}
