# Diagramas del Sistema - Gestión de Baches

Este documento contiene los diagramas del sistema de gestión de baches del Departamento de Obras Públicas.

## 1. Diagrama de Clases Completo

```mermaid
classDiagram
    direction TB

    class Ciudadano {
        -nombre: String
        -email: String
        -contrasenia: int
        +Ciudadano()
        +Ciudadano(nombre: String, contrasenia: int)
        +Ciudadano(nombre: String, email: String, contrasenia: int)
        +validarIngreso(psw: int) boolean
        +cambioPassword(newPass: String) void
        +delay(mili: int) void
        +ciudadanosDiferentes(c2: Ciudadano) boolean
        +getNombre() String
        +getEmail() String
        +setEmail(email: String) void
        +getContrasenia() int
    }

    class Bache {
        -id: int
        -tamanio: double
        -calleA: String
        -calleB: String
        -altura: int
        -barrio: String
        -prioridad: int
        -estado: String
        +Bache(id: int, tamanio: double, calleA: String, calleB: String, altura: int, barrio: String, prioridad: int)
        +getValores() String
        +isReparado() boolean
        +setEstado(estado: String) void
        +getId() int
        +getPrioridad() int
        +getEstado() String
        +getCalleA() String
        +getCalleB() String
        +getAltura() int
        +getBarrio() String
        +getTamanio() double
    }

    class InformeRotura {
        -codInf: String
        -fechaInforme: LocalDate
        -problema: String
        -codigoUrgencia: int
        -bache: Bache
        -ciudadano: Ciudadano
        -pedidoReparacion: PedidoObra
        +InformeRotura(codInf: String, fechaInforme: LocalDate, problema: String, codigoUrgencia: int, bache: Bache, ciudadano: Ciudadano)
        +getPedidoReparacion() PedidoObra
        +setPedidoReparacion(pedido: PedidoObra) void
        +getCodInf() String
        +getFechaInforme() LocalDate
        +getProblema() String
        +getCodigoUrgencia() int
        +getBache() Bache
        +getCiudadano() Ciudadano
    }

    class PedidoObra {
        -numeroPedido: int
        -ubicacion: String
        -tamanio: double
        -fechaCreacion: LocalDate
        -fechaReparacion: LocalDate
        -observaciones: String
        -brigada: Brigada
        +PedidoObra(numeroPedido: int, ubicacion: String, tamanio: double, fechaCreacion: LocalDate)
        +getFechaReparacion() LocalDate
        +setFechaReparacion(fecha: LocalDate) void
        +getObservaciones() String
        +setObservaciones(obs: String) void
        +getBrigada() Brigada
        +setBrigada(b: Brigada) void
        +getNumeroPedido() int
        +getUbicacion() String
        +getTamanio() double
        +getFechaCreacion() LocalDate
    }

    class Trabajador {
        -id: int
        -nombre: String
        -puesto: String
        -libre: boolean
        +Trabajador(id: int, nombre: String, puesto: String)
        +isLibre() boolean
        +setLibre(libre: boolean) void
        +getId() int
        +getNombre() String
        +getPuesto() String
    }

    class Brigada {
        -numero: int
        -nombre: String
        -cantidadTrabajadores: int
        -equipamiento: String
        -materiales: String
        -jefe: Trabajador
        -trabajadores: List~Trabajador~
        +Brigada(numero: int, nombre: String, equipamiento: String, materiales: String, jefe: Trabajador, trabajadores: List~Trabajador~)
        +getNumero() int
        +getNombre() String
        +getCantidadTrabajadores() int
        +getEquipamiento() String
        +getMateriales() String
        +getJefe() Trabajador
        +getTrabajadores() List~Trabajador~
    }

    class SistemaObrasPublicas {
        -ciudadanos: List~Ciudadano~
        -baches: List~Bache~
        -informes: List~InformeRotura~
        -pedidos: List~PedidoObra~
        -brigadas: List~Brigada~
        -trabajadores: List~Trabajador~
        +buscarCiudadano(nombre: String) Ciudadano
        +verificarInterseccion(calleA: String, calleB: String, altura: int) boolean
        +buscarBachePorInterseccion(calleA: String, calleB: String, altura: int) Bache
        +buscarInformePorBache(bache: Bache) InformeRotura
        +registrarCiudadano(nombre: String, email: String) Ciudadano
        +reportarBache(ciudadanoNombre: String, email: String, tamanio: double, calleA: String, calleB: String, altura: int, barrio: String, prioridad: int, problema: String) String
        +crearPedidoObra(codInforme: String) PedidoObra
        +asignarBrigada(numPedido: int, numBrigada: int) void
        +completarReparacion(numPedido: int, observaciones: String, fechaFin: LocalDate) void
        +informesPorCiudadano(c: Ciudadano) List~InformeRotura~
        +informesSinPedidos() List~InformeRotura~
        +informeBrigadasOciosas() List~Brigada~
        +pedidosPorBrigada(b: Brigada) List~PedidoObra~
        +bachesSinReparar() List~Bache~
        +trabajadoresPorBrigada(b: Brigada) List~Trabajador~
    }

    %% Relaciones Materializadas y Cardinalidades
    Ciudadano "1" --> "*" InformeRotura : "informa / denuncia"
    Bache "1" <-- "1" InformeRotura : "vincula"
    InformeRotura "1" --> "0..1" PedidoObra : "tiene asignado"
    PedidoObra "0..*" --> "0..1" Brigada : "se asigna a"
    Brigada "0..*" --> "1" Trabajador : "dirigida por (jefe)"
    Brigada "0..*" --> "3" Trabajador : "conformada por (trabajadores)"
    
    %% Relación de agregación del sistema con los elementos
    SistemaObrasPublicas --> "*" Ciudadano
    SistemaObrasPublicas --> "*" Bache
    SistemaObrasPublicas --> "*" InformeRotura
    SistemaObrasPublicas --> "*" PedidoObra
    SistemaObrasPublicas --> "*" Brigada
    SistemaObrasPublicas --> "*" Trabajador
```

---

## 2. Diagramas de Secuencia

### Caso A: Ciudadano denuncia un bache

```mermaid
sequenceDiagram
    autonumber
    actor C as Ciudadano
    participant IU as IU Pantalla
    participant S as SistemaObrasPublicas
    participant P as IU Impresor

    C->>IU: Ingresa datos (nombre, email/tel)
    IU->>S: buscarCiudadano(nombre)
    alt Ciudadano Existe
        S-->>IU: Ciudadano encontrado
    else Ciudadano No Existe
        S->>S: registrarCiudadano(nombre, email)
        S-->>IU: Ciudadano registrado
    end

    C->>IU: Ingresa ubicación (calleA, calleB, altura, barrio) y detalles
    IU->>S: verificarInterseccion(calleA, calleB, altura)
    S-->>IU: Intersección válida

    IU->>S: reportarBache(datos...)
    S->>S: buscarBachePorInterseccion(calleA, calleB, altura)
    alt Bache ya denunciado (Existe Informe)
        S->>S: buscarInformePorBache(bache)
        S-->>IU: Comunica fecha de informe existente
    else Nuevo Bache
        S->>S: crear Bache(estado: "sin reparar")
        S->>S: crear InformeRotura(codInf, fecha, problema, urgencia)
        S->>P: Ordena impresión de constancia
        P-->>S: Constancia impresa
        S-->>IU: Datos del bache + fecha estimada de reparación
    end
```

### Caso B: Elaboración de Pedido de Obra por Prioridad

```mermaid
sequenceDiagram
    autonumber
    actor R as Representante (Mostrador)
    participant S as SistemaObrasPublicas

    R->>S: Buscar informes de rotura por prioridad (1-5)
    S-->>R: Listado de informes ordenados
    R->>S: crearPedidoObra(codInforme)
    S->>S: Obtener InformeRotura y Bache asociado
    S->>S: Instanciar PedidoObra(num, ubicacion, tamanio, fechaCreacion: now)
    S->>S: Calcular fechaReparacion (fechaCreacion + 7 días)
    S->>S: Asociar PedidoObra al InformeRotura
    S-->>R: Pedido de obra elaborado con éxito
```

### Caso C: Asignación Diaria de Brigadas y Ejecución de Obra

```mermaid
sequenceDiagram
    autonumber
    actor S as Sistema / Proceso Diario
    participant PO as PedidoObra
    participant BR as Brigada
    participant T as Trabajadores

    S->>S: Buscar pedidos de obra pendientes del día
    S->>S: Buscar brigadas libres (informeBrigadasOciosas())
    loop Por cada pedido y brigada libre
        S->>S: asignarBrigada(numPedido, numBrigada)
        S->>PO: setBrigada(brigada)
        S->>BR: Marcar jefe y trabajadores como ocupados
        Note over BR, T: Se entrega el pedido al responsable de la brigada
    end
    
    Note over BR, T: Brigada realiza la reparación del bache
    
    actor J as Jefe de Brigada
    J->>S: completarReparacion(numPedido, observaciones, fechaFin)
    S->>PO: setFechaReparacion(fechaFin), setObservaciones(obs)
    S->>PO: getBache()
    S->>PO: setEstado("reparado")
    S->>BR: Marcar jefe y trabajadores como libres
    S->>S: Enviar mail con composición del pedido impreso al Ciudadano
    S-->>J: Confirmación de obra finalizada y brigada liberada
```
