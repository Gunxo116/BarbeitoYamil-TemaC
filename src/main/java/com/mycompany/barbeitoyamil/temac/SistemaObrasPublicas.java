package com.mycompany.barbeitoyamil.temac;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaObrasPublicas {
    private List<Ciudadano> ciudadanos;
    private List<Bache> baches;
    private List<InformeRotura> informes;
    private List<PedidoObra> pedidos;
    private List<Brigada> brigadas;
    private List<Trabajador> trabajadores;

    public SistemaObrasPublicas() {
        this.ciudadanos = new ArrayList<>();
        this.baches = new ArrayList<>();
        this.informes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.brigadas = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
    }

    public Ciudadano buscarCiudadano(String nombre) {
        for (Ciudadano c : ciudadanos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public boolean verificarInterseccion(String calleA, String calleB, int altura) {
        return calleA != null && !calleA.trim().isEmpty() &&
               calleB != null && !calleB.trim().isEmpty() &&
               altura > 0;
    }

    public Bache buscarBachePorInterseccion(String calleA, String calleB, int altura) {
        for (Bache b : baches) {
            if (b.getCalleA().equalsIgnoreCase(calleA) &&
                b.getCalleB().equalsIgnoreCase(calleB) &&
                b.getAltura() == altura) {
                return b;
            }
        }
        return null;
    }

    public InformeRotura buscarInformePorBache(Bache b) {
        for (InformeRotura inf : informes) {
            if (inf.getBache().equals(b)) {
                return inf;
            }
        }
        return null;
    }

    public Ciudadano registrarCiudadano(String nombre, String email) {
        Ciudadano nuevo = new Ciudadano(nombre, 1212);
        nuevo.setEmail(email);
        ciudadanos.add(nuevo);
        return nuevo;
    }

    public String reportarBache(String ciudadanoNombre, String email, double tamanio, 
                                 String calleA, String calleB, int altura, String barrio, 
                                 int prioridad, String problema) {
        Ciudadano c = buscarCiudadano(ciudadanoNombre);
        if (c == null) {
            c = registrarCiudadano(ciudadanoNombre, email);
        }

        if (!verificarInterseccion(calleA, calleB, altura)) {
            return "ERROR: Intersección inválida.";
        }

        Bache bacheExistente = buscarBachePorInterseccion(calleA, calleB, altura);
        if (bacheExistente != null) {
            InformeRotura infExistente = buscarInformePorBache(bacheExistente);
            if (infExistente != null) {
                return "El bache ya está denunciado. Fecha del informe: " + infExistente.getFechaInforme();
            }
        }

        int bacheId = baches.size() + 1;
        Bache nuevoBache = new Bache(bacheId, tamanio, calleA, calleB, altura, barrio, prioridad);
        baches.add(nuevoBache);

        String codInf = "INF-" + (informes.size() + 1);
        InformeRotura nuevoInforme = new InformeRotura(codInf, LocalDate.now(), problema, prioridad, nuevoBache, c);
        informes.add(nuevoInforme);

        LocalDate fechaEstimada = LocalDate.now().plusDays(10);
        return "Denuncia generada: " + codInf + ". Ubicación: " + calleA + " y " + calleB + ". Fecha estimada de reparación: " + fechaEstimada;
    }

    // Elaborar Pedido de Obra (Caso B de Secuencia)
    public PedidoObra crearPedidoObra(String codInforme) {
        InformeRotura inf = null;
        for (InformeRotura i : informes) {
            if (i.getCodInf().equalsIgnoreCase(codInforme)) {
                inf = i;
                break;
            }
        }

        if (inf == null) {
            System.out.println("Informe de rotura no encontrado.");
            return null;
        }

        if (inf.getPedidoReparacion() != null) {
            return inf.getPedidoReparacion();
        }

        Bache b = inf.getBache();
        int numPedido = pedidos.size() + 1;
        String ubicacion = b.getCalleA() + " y " + b.getCalleB() + " Nro " + b.getAltura() + " (" + b.getBarrio() + ")";
        
        PedidoObra nuevoPedido = new PedidoObra(numPedido, ubicacion, b.getTamanio(), LocalDate.now());
        pedidos.add(nuevoPedido);
        inf.setPedidoReparacion(nuevoPedido);

        return nuevoPedido;
    }

    public void asignarBrigada(int numPedido, int numBrigada) {
        PedidoObra po = null;
        for (PedidoObra p : pedidos) {
            if (p.getNumeroPedido() == numPedido) {
                po = p;
                break;
            }
        }

        Brigada br = null;
        for (Brigada b : brigadas) {
            if (b.getNumero() == numBrigada) {
                br = b;
                break;
            }
        }

        if (po != null && br != null) {
            po.setBrigada(br);
            if (br.getJefe() != null) {
                br.getJefe().setLibre(false);
            }
            for (Trabajador t : br.getTrabajadores()) {
                t.setLibre(false);
            }
        }
    }

    public void completarReparacion(int numPedido, String observaciones, LocalDate fechaFin) {
        PedidoObra po = null;
        for (PedidoObra p : pedidos) {
            if (p.getNumeroPedido() == numPedido) {
                po = p;
                break;
            }
        }

        if (po != null) {
            po.setFechaReparacion(fechaFin);
            po.setObservaciones(observaciones);

            for (InformeRotura inf : informes) {
                if (inf.getPedidoReparacion() != null && inf.getPedidoReparacion().getNumeroPedido() == numPedido) {
                    inf.getBache().setEstado("reparado");
                    System.out.println("ENVIANDO MAIL A " + inf.getCiudadano().getEmail() + " -> Bache en " + po.getUbicacion() + " REPARADO el " + fechaFin);
                    break;
                }
            }

            Brigada br = po.getBrigada();
            if (br != null) {
                if (br.getJefe() != null) {
                    br.getJefe().setLibre(true);
                }
                for (Trabajador t : br.getTrabajadores()) {
                    t.setLibre(true);
                }
            }
        }
    }

    public List<InformeRotura> informesPorCiudadano(Ciudadano c) {
        return informes.stream()
                .filter(inf -> inf.getCiudadano().getNombre().equalsIgnoreCase(c.getNombre()))
                .collect(Collectors.toList());
    }

    public List<InformeRotura> informesSinPedidos() {
        return informes.stream()
                .filter(inf -> inf.getPedidoReparacion() == null)
                .collect(Collectors.toList());
    }

    public List<Brigada> informeBrigadasOciosas() {
        List<Brigada> ociosas = new ArrayList<>();
        for (Brigada b : brigadas) {
            if (b.getJefe() != null && b.getJefe().isLibre()) {
                ociosas.add(b);
            }
        }
        return ociosas;
    }

    public List<PedidoObra> pedidosPorBrigada(Brigada b) {
        return pedidos.stream()
                .filter(p -> p.getBrigada() != null && p.getBrigada().getNumero() == b.getNumero())
                .collect(Collectors.toList());
    }

    public List<Bache> bachesSinReparar() {
        return baches.stream()
                .filter(b -> !b.isReparado())
                .collect(Collectors.toList());
    }

    public List<Trabajador> trabajadoresPorBrigada(Brigada b) {
        List<Trabajador> lista = new ArrayList<>();
        if (b.getJefe() != null) {
            lista.add(b.getJefe());
        }
        lista.addAll(b.getTrabajadores());
        return lista;
    }

    // Getters para test y simulación
    public List<Ciudadano> getCiudadanos() {
        return ciudadanos;
    }

    public List<Bache> getBaches() {
        return baches;
    }

    public List<InformeRotura> getInformes() {
        return informes;
    }

    public List<PedidoObra> getPedidos() {
        return pedidos;
    }

    public List<Brigada> getBrigadas() {
        return brigadas;
    }

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }
}
