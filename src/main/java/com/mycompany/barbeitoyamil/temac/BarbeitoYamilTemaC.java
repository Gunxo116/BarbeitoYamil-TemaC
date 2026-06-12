package com.mycompany.barbeitoyamil.temac;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BarbeitoYamilTemaC {

    public static void main(String[] args) {
        System.out.println("== SIMULACION SISTEMA DE GESTION DE BACHES ==");

        SistemaObrasPublicas sistema = new SistemaObrasPublicas();

        Trabajador t1 = new Trabajador(101, "Carlos Gomez", "Obrero");
        Trabajador t2 = new Trabajador(102, "Juan Perez", "Obrero");
        Trabajador t3 = new Trabajador(103, "Luis Lopez", "Obrero");
        Trabajador jefe1 = new Trabajador(104, "Pedro Martinez", "Jefe de Brigada");

        sistema.getTrabajadores().add(t1);
        sistema.getTrabajadores().add(t2);
        sistema.getTrabajadores().add(t3);
        sistema.getTrabajadores().add(jefe1);

        List<Trabajador> obrerosAlfa = Arrays.asList(t1, t2, t3);
        Brigada b1 = new Brigada(1, "Alfa", "Pala, Compresora, Camioneta", "Asfalto frío, Gravilla", jefe1,
                obrerosAlfa);
        sistema.getBrigadas().add(b1);

        System.out.println("\n--- CASO A: Reporte de Bache por un ciudadano (San Luis) ---");
        String reporte = sistema.reportarBache(
                "Yamil Barbeito",
                "yamil.barbeito@gmail.com",
                3.2,
                "Av. Illia",
                "Chacabuco",
                450,
                "Barrio El Lince",
                1,
                "Tremendo pozo en la esquina, peligro de accidente");
        System.out.println(reporte);

        Bache bache = sistema.getBaches().get(0);
        System.out.println("Bache cargado en sistema: " + bache.getValores());

        System.out.println("\n--- CASO B: Creación de Pedido de Obra ---");
        InformeRotura informe = sistema.getInformes().get(0);
        PedidoObra pedido = sistema.crearPedidoObra(informe.getCodInf());
        if (pedido != null) {
            System.out.println("Pedido de Obra Nro " + pedido.getNumeroPedido() + " creado para ubicación: "
                    + pedido.getUbicacion());
        }

        System.out.println("\n--- CASO C: Asignación de Brigada y Ejecución ---");
        System.out.println("Brigadas ociosas antes de asignar: " + sistema.informeBrigadasOciosas().size());

        sistema.asignarBrigada(pedido.getNumeroPedido(), b1.getNumero());
        System.out.println("Brigada asignada al pedido.");
        System.out.println("Brigadas ociosas después de asignar: " + sistema.informeBrigadasOciosas().size());

        System.out.println("\nFinalizando reparación...");
        sistema.completarReparacion(pedido.getNumeroPedido(), "Reparación con asfalto caliente, bache nivelado.",
                LocalDate.now());

        System.out.println("Estado actual del bache: " + bache.getEstado());

        System.out.println("\n=== REPORTES Y LISTADOS DEL SISTEMA ===");

        System.out.println("\n1. Informes por Ciudadano (Yamil Barbeito):");
        Ciudadano yamil = sistema.buscarCiudadano("Yamil Barbeito");
        for (InformeRotura inf : sistema.informesPorCiudadano(yamil)) {
            System.out.println(" - " + inf.getCodInf() + " | Problema: " + inf.getProblema());
        }

        System.out.println("\n2. Informes sin Pedidos:");
        System.out.println(" Cantidad: " + sistema.informesSinPedidos().size());

        System.out.println("\n3. Brigadas Ociosas:");
        for (Brigada br : sistema.informeBrigadasOciosas()) {
            System.out.println(" - Brigada Nro " + br.getNumero() + " (" + br.getNombre() + ")");
        }

        System.out.println("\n4. Pedidos por Brigada (Alfa):");
        for (PedidoObra po : sistema.pedidosPorBrigada(b1)) {
            System.out.println(" - Pedido Nro " + po.getNumeroPedido() + " | Estado: "
                    + (po.getFechaReparacion() != null ? "Reparado el " + po.getFechaReparacion() : "Pendiente"));
        }

        System.out.println("\n5. Baches sin Reparar:");
        System.out.println(" Cantidad: " + sistema.bachesSinReparar().size());

        System.out.println("\n6. Trabajadores por Brigada (Alfa):");
        for (Trabajador tr : sistema.trabajadoresPorBrigada(b1)) {
            System.out.println(" - " + tr.getNombre() + " | Puesto: " + tr.getPuesto() + " | Estado: "
                    + (tr.isLibre() ? "Libre" : "Ocupado"));
        }
    }
}
