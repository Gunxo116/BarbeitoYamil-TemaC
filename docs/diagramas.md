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

En esta sección se presentan los diagramas de secuencia utilizando tanto la notación **PlantUML** (con patrones de diseño Boundary-Control-Entity) como su representación equivalente en **Mermaid** para visualización directa en Markdown.

### Caso A: Ciudadano denuncia un bache

#### PlantUML
```plantuml
@startuml DSA_DenunciaBache
!theme plain
skinparam backgroundColor #FFFFFF
skinparam shadowing false
skinparam dpi 155
skinparam defaultFontName Arial
skinparam defaultFontSize 12
skinparam actorStyle awesome
skinparam sequenceArrowThickness 1.4
skinparam sequenceArrowColor #3A8FD4
skinparam sequenceLifeLineBorderColor #3A8FD4
skinparam sequenceLifeLineBackgroundColor #EBF5FB
skinparam participantBackgroundColor #1E293B
skinparam participantFontColor #FFFFFF
skinparam participantFontSize 12
skinparam participantBorderColor #1E293B
skinparam actorBackgroundColor #EAF4FC
skinparam actorFontColor #111827
skinparam actorBorderColor #3A8FD4
skinparam noteBackgroundColor #FFF9C4
skinparam noteBorderColor #FF9E42

title Diagrama de Secuencia A — Ciudadano denuncia un bache

' ===== BOUNDARY =====
actor "Ciudadano" as CIU #D6EAF8
participant "IU Pantalla" as IUP
participant "IU Impresor" as IUI

' ===== CONTROL =====
participant "Gestor de\nDenuncia" as GD
participant "Gestor de\nInformes" as GI

' ===== ENTITY =====
participant "Ciudadano\n[Entidad]" as CIU_E
participant "Interseccion" as INT
participant "Bache" as BAC
participant "InformeRotura" as INF

== Búsqueda del ciudadano ==
CIU -> IUP : ingresarDatos(idCiudadano)
IUP -> GD : buscarCiudadano(id)
GD -> CIU_E : buscar(id)
CIU_E --> GD : ciudadano

alt Ciudadano no existe
    create CIU_E
    GD -> CIU_E : registrar(nombre, mail)
    CIU_E --> GD : ciudadanoNuevo
    GD --> IUP : solicitarDatosRegistro()
    IUP --> CIU : pedirNombreYMail()
    CIU -> IUP : ingresarNombre(nombre)
    CIU -> IUP : ingresarMail(mail)
    IUP -> GD : confirmarRegistro(nombre, mail)
else Ciudadano encontrado
    GD --> IUP : mostrarCiudadano(ciudadano)
end

== Carga del bache ==
IUP --> CIU : pedirUbicacionBache()
CIU -> IUP : ingresarUbicacion(calleA, calleB, altura, barrio, tamanio, prioridad)
IUP -> GD : cargarBache(datos)
GD -> INT : verificarInterseccion(calleA, calleB, altura, barrio)
INT --> GD : interseccionValida

alt Intersección inválida
    GD --> IUP : mostrarError("Intersección no válida")
    IUP --> CIU : solicitarCorreccion()
else Intersección válida
    create BAC
    GD -> BAC : new Bache(tamanio, prioridad, interseccion, ciudadano)
    BAC --> GD : bacheCreado

    == Generación del informe ==
    GD -> GI : generarInforme(bache, ciudadano)
    create INF
    GI -> INF : new InformeRotura(problema, codUrgencia, bache, ciudadano)
    INF --> GI : informeCreado

    alt Ya existe un informe para ese bache
        GI --> IUP : mostrarFechaInformeExistente(fecha)
        IUP --> CIU : informarFechaExistente(fecha)
    else Informe nuevo
        GI --> IUP : mostrarConfirmacion(informe)
        IUP -> GD : calcularFechaEstimada(prioridad)
        GD --> IUP : fechaEstimada
        IUP --> CIU : mostrarDatosYFechaEstimada(informe, fechaEstimada)

        == Impresión de constancia ==
        IUP -> IUI : imprimirConstancia(informe, fechaEstimada)
        IUI --> IUP : constanciaImpresa
    end
end
@enduml
```

#### Mermaid
```mermaid
sequenceDiagram
    autonumber
    actor CIU as Ciudadano
    participant IUP as IU Pantalla
    participant GD as Gestor de Denuncia
    participant CIU_E as Ciudadano (Entidad)
    participant INT as Interseccion
    participant BAC as Bache
    participant GI as Gestor de Informes
    participant INF as InformeRotura
    participant IUI as IU Impresor

    Note over CIU, GD: Búsqueda del ciudadano
    CIU->>IUP: ingresarDatos(idCiudadano)
    IUP->>GD: buscarCiudadano(id)
    GD->>CIU_E: buscar(id)
    CIU_E-->>GD: ciudadano

    alt Ciudadano no existe
        GD->>CIU_E: registrar(nombre, mail)
        CIU_E-->>GD: ciudadanoNuevo
        GD-->>IUP: solicitarDatosRegistro()
        IUP-->>CIU: pedirNombreYMail()
        CIU->>IUP: ingresarNombre(nombre)
        CIU->>IUP: ingresarMail(mail)
        IUP->>GD: confirmarRegistro(nombre, mail)
    else Ciudadano encontrado
        GD-->>IUP: mostrarCiudadano(ciudadano)
    end

    Note over CIU, INT: Carga del bache
    IUP-->>CIU: pedirUbicacionBache()
    CIU->>IUP: ingresarUbicacion(calleA, calleB, altura, barrio, tamanio, prioridad)
    IUP->>GD: cargarBache(datos)
    GD->>INT: verificarInterseccion(calleA, calleB, altura, barrio)
    INT-->>GD: interseccionValida

    alt Intersección inválida
        GD-->>IUP: mostrarError("Intersección no válida")
        IUP-->>CIU: solicitarCorreccion()
    else Intersección válida
        GD->>BAC: new Bache(tamanio, prioridad, interseccion, ciudadano)
        BAC-->>GD: bacheCreado

        Note over GD, INF: Generación del informe
        GD->>GI: generarInforme(bache, ciudadano)
        GI->>INF: new InformeRotura(problema, codUrgencia, bache, ciudadano)
        INF-->>GI: informeCreado

        alt Ya existe un informe para ese bache
            GI-->>IUP: mostrarFechaInformeExistente(fecha)
            IUP-->>CIU: informarFechaExistente(fecha)
        else Informe nuevo
            GI-->>IUP: mostrarConfirmacion(informe)
            IUP->>GD: calcularFechaEstimada(prioridad)
            GD-->>IUP: fechaEstimada
            IUP-->>CIU: mostrarDatosYFechaEstimada(informe, fechaEstimada)

            Note over IUP, IUI: Impresión de constancia
            IUP->>IUI: imprimirConstancia(informe, fechaEstimada)
            IUI-->>IUP: constanciaImpresa
        end
    end
```

---

### Caso B: Representante elabora pedido de obra

#### PlantUML
```plantuml
@startuml DSB_PedidoObra
!theme plain
skinparam backgroundColor #FFFFFF
skinparam shadowing false
skinparam dpi 180
skinparam defaultFontName Arial
skinparam defaultFontSize 12
skinparam actorStyle awesome
skinparam sequenceArrowThickness 1.4
skinparam sequenceArrowColor #3A8FD4
skinparam sequenceLifeLineBorderColor #3A8FD4
skinparam sequenceLifeLineBackgroundColor #EBF5FB
skinparam participantBackgroundColor #1E293B
skinparam participantFontColor #FFFFFF
skinparam participantFontSize 12
skinparam participantBorderColor #1E293B
skinparam actorBackgroundColor #EAF4FC
skinparam actorFontColor #111827
skinparam actorBorderColor #3A8FD4
skinparam noteBackgroundColor #FFF9C4
skinparam noteBorderColor #FF9E42

title Diagrama de Secuencia B — Representante elabora pedido de obra

' ===== BOUNDARY =====
actor "Representante" as REP #D6EAF8
participant "IU Mostrador" as IUM

' ===== CONTROL =====
participant "Gestor de\nPedidos" as GP

' ===== ENTITY =====
participant "InformeRotura" as INF
participant "Bache" as BAC
participant "PedidoObra" as PED

== Búsqueda del informe por prioridad ==
REP -> IUM : solicitarInformePorPrioridad()
IUM -> GP : buscarInformePorPrioridad()
GP -> INF : informesSinPedidos()
INF --> GP : listaInformes

alt No hay informes sin pedido
    GP --> IUM : mostrarMensaje("No hay informes pendientes")
    IUM --> REP : informarSinPendientes()
else Hay informes pendientes
    GP --> IUM : mostrarListaInformes(listaInformes)
    REP -> IUM : seleccionarInforme(informe)
    IUM -> GP : confirmarInforme(informe)

    == Elaboración del pedido de obra ==
    GP -> BAC : obtenerDatos(informe.bache)
    BAC --> GP : ubicacion, tamanio

    GP -> GP : calcularFechaReparacion(LocalDate.now().plusDays(7))

    create PED
    GP -> PED : new PedidoObra(nro, ubicacion, tamanio, now, fechaReparacion)
    PED --> GP : pedidoCreado

    GP -> INF : setPedidoObra(pedido)
    INF --> GP : ok

    GP --> IUM : mostrarPedidoCreado(pedido)
    IUM --> REP : confirmarPedidoElaborado(pedido)
end
@enduml
```

#### Mermaid
```mermaid
sequenceDiagram
    autonumber
    actor REP as Representante
    participant IUM as IU Mostrador
    participant GP as Gestor de Pedidos
    participant INF as InformeRotura
    participant BAC as Bache
    participant PED as PedidoObra

    Note over REP, INF: Búsqueda del informe por prioridad
    REP->>IUM: solicitarInformePorPrioridad()
    IUM->>GP: buscarInformePorPrioridad()
    GP->>INF: informesSinPedidos()
    INF-->>GP: listaInformes

    alt No hay informes sin pedido
        GP-->>IUM: mostrarMensaje("No hay informes pendientes")
        IUM-->>REP: informarSinPendientes()
    else Hay informes pendientes
        GP-->>IUM: mostrarListaInformes(listaInformes)
        REP->>IUM: seleccionarInforme(informe)
        IUM->>GP: confirmarInforme(informe)

        Note over GP, PED: Elaboración del pedido de obra
        GP->>BAC: obtenerDatos(informe.bache)
        BAC-->>GP: ubicacion, tamanio
        GP->>GP: calcularFechaReparacion(LocalDate.now().plusDays(7))
        GP->>PED: new PedidoObra(nro, ubicacion, tamanio, now, fechaReparacion)
        PED-->>GP: pedidoCreado
        GP->>INF: setPedidoObra(pedido)
        INF-->>GP: ok
        GP-->>IUM: mostrarPedidoCreado(pedido)
        IUM-->>REP: confirmarPedidoElaborado(pedido)
    end
```

---

### Caso C: Asignación diaria de brigada

#### PlantUML
```plantuml
@startuml DSC_AsignacionBrigada
!theme plain
skinparam backgroundColor #FFFFFF
skinparam shadowing false
skinparam dpi 180
skinparam defaultFontName Arial
skinparam defaultFontSize 12
skinparam actorStyle awesome
skinparam sequenceArrowThickness 1.4
skinparam sequenceArrowColor #3A8FD4
skinparam sequenceLifeLineBorderColor #3A8FD4
skinparam sequenceLifeLineBackgroundColor #EBF5FB
skinparam participantBackgroundColor #1E293B
skinparam participantFontColor #FFFFFF
skinparam participantFontSize 12
skinparam participantBorderColor #1E293B
skinparam actorBackgroundColor #EAF4FC
skinparam actorFontColor #111827
skinparam actorBorderColor #3A8FD4
skinparam noteBackgroundColor #FFF9C4
skinparam noteBorderColor #FF9E42

title Diagrama de Secuencia C — Asignación diaria de brigada

' ===== BOUNDARY =====
actor "Sistema\n(Temporizador)" as TMP #D5D8DC
participant "IU Gestión" as IUG

' ===== CONTROL =====
participant "Gestor de\nAsignaciones" as GA

' ===== ENTITY =====
participant "PedidoObra" as PED
participant "BrigadaReparacion" as BRI
participant "Trabajador" as TRA
participant "JefeBrigada" as JEF

== Búsqueda de pedidos del día ==
[-> GA : ejecutarAsignacionDiaria()
GA -> PED : buscarPedidosDelDia(LocalDate.now())
PED --> GA : listaPedidos

alt No hay pedidos para hoy
    GA --> IUG : mostrarMensaje("Sin pedidos para hoy")
else Hay pedidos pendientes
    loop Para cada pedido sin brigada
        GA -> BRI : buscarBrigadasLibres()
        BRI --> GA : listaBrigadas

        alt No hay brigadas libres
            GA --> IUG : mostrarAlerta("Sin brigadas disponibles")
        else Hay brigadas disponibles
            GA -> BRI : seleccionarBrigada(listaBrigadas)
            BRI --> GA : brigadaSeleccionada

            == Conformación de la brigada ==
            GA -> TRA : buscarTrabajadoresLibres(3)
            TRA --> GA : listaTrabajadores
            note right : Se seleccionan\nexactamente 3 trabajadores

            loop Para cada trabajador
                GA -> TRA : setLibre(false)
                TRA --> GA : ok
                GA -> BRI : asignarTrabajador(trabajador)
                BRI --> GA : ok
            end

            GA -> JEF : asignarJefe(brigada)
            JEF --> GA : ok

            == Entrega del pedido ==
            GA -> PED : setBrigada(brigada)
            PED --> GA : ok
            GA -> BRI : setLibre(false)
            BRI --> GA : ok

            GA -> JEF : agregarFechaFin(pedido, fechaReparacion)
            JEF --> GA : fechaAgregada

            GA --> IUG : mostrarAsignacion(pedido, brigada)
        end
    end
end
@enduml
```

#### Mermaid
```mermaid
sequenceDiagram
    autonumber
    actor TMP as Sistema (Temporizador)
    participant IUG as IU Gestión
    participant GA as Gestor de Asignaciones
    participant PED as PedidoObra
    participant BRI as BrigadaReparacion
    participant TRA as Trabajador
    participant JEF as JefeBrigada

    Note over TMP, PED: Búsqueda de pedidos del día
    [->>GA: ejecutarAsignacionDiaria()
    GA->>PED: buscarPedidosDelDia(LocalDate.now())
    PED-->>GA: listaPedidos

    alt No hay pedidos para hoy
        GA-->>IUG: mostrarMensaje("Sin pedidos para hoy")
    else Hay pedidos pendientes
        loop Para cada pedido sin brigada
            GA->>BRI: buscarBrigadasLibres()
            BRI-->>GA: listaBrigadas

            alt No hay brigadas libres
                GA-->>IUG: mostrarAlerta("Sin brigadas disponibles")
            else Hay brigadas disponibles
                GA->>BRI: seleccionarBrigada(listaBrigadas)
                BRI-->>GA: brigadaSeleccionada

                Note over GA, TRA: Conformación de la brigada
                GA->>TRA: buscarTrabajadoresLibres(3)
                TRA-->>GA: listaTrabajadores

                loop Para cada trabajador de los 3 seleccionados
                    GA->>TRA: setLibre(false)
                    TRA-->>GA: ok
                    GA->>BRI: asignarTrabajador(trabajador)
                    BRI-->>GA: ok
                end

                GA->>JEF: asignarJefe(brigada)
                JEF-->>GA: ok

                Note over GA, PED: Entrega del pedido
                GA->>PED: setBrigada(brigada)
                PED-->>GA: ok
                GA->>BRI: setLibre(false)
                BRI-->>GA: ok

                GA->>JEF: agregarFechaFin(pedido, fechaReparacion)
                JEF-->>GA: fechaAgregada

                GA-->>IUG: mostrarAsignacion(pedido, brigada)
            end
        end
    end
```

