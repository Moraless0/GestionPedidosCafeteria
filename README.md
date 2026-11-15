# Gestión de Pedidos — Cafetería La Antigua

Aplicación de consola desarrollada con **Spring Boot** para gestionar pedidos de una cafetería guatemalteca. El taller aplica inyección de dependencias por constructor, beans administrados por Spring (`@Component` / `@Service`) y ejecución del flujo principal mediante `CommandLineRunner`.

## Requisitos

- Java 21
- Maven (incluido como wrapper: `mvnw` / `mvnw.cmd`)

## Cómo ejecutar

Desde la raíz del proyecto:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

La aplicación arranca el contexto de Spring y muestra el menú principal en consola.

## Menú principal

```
===== CAFETERÍA LA ANTIGUA =====
1. Ver menú de productos
2. Crear pedido
3. Ver resumen de ventas
4. Salir
```

### Catálogo de productos

| Código | Producto                        | Precio   |
|--------|----------------------------------|----------|
| C01    | Café de Huehuetenango            | Q 18.00  |
| C02    | Café de Antigua (Americano)      | Q 15.00  |
| C03    | Par de Champurradas              | Q 10.00  |
| C04    | Rellenitos de Plátano            | Q 12.00  |
| C05    | Chuchito Tradicional             | Q 10.00  |
| C06    | Atol de Elote                    | Q 14.00  |
| C07    | Molletes (2 unidades)            | Q 20.00  |

### Crear pedido

El sistema genera un número correlativo (`PED-001`, `PED-002`, ...). Se ingresan códigos de producto (acepta mayúsculas o minúsculas) y cantidades hasta decidir finalizar (`F`) o cancelar (`C`). Al finalizar se imprime la factura con subtotal, IVA (12%) y total en Quetzales (`Q`).

## Estructura del proyecto

```
com.cafeteria.GestionPedidosCafeteria
├── model        → POJOs: Producto, DetallePedido, Pedido
├── repository   → ProductoRepository (@Component, catálogo en memoria)
├── service      → CalculadoraService, PedidoService, FacturaService (@Service)
└── runner       → CafeteriaRunner (@Component, CommandLineRunner)
```

