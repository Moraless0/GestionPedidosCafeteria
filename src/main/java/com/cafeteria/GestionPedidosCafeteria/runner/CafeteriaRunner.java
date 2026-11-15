package com.cafeteria.GestionPedidosCafeteria.runner;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cafeteria.GestionPedidosCafeteria.model.Pedido;
import com.cafeteria.GestionPedidosCafeteria.model.Producto;
import com.cafeteria.GestionPedidosCafeteria.repository.ProductoRepository;
import com.cafeteria.GestionPedidosCafeteria.service.FacturaService;
import com.cafeteria.GestionPedidosCafeteria.service.PedidoService;

@Component
public class CafeteriaRunner implements CommandLineRunner {

	private final ApplicationContext contexto;

	public CafeteriaRunner(ApplicationContext contexto) {
		this.contexto = contexto;
	}

	@Override
	public void run(String... args) {
		ProductoRepository productoRepository = contexto.getBean(ProductoRepository.class);
		PedidoService pedidoService = contexto.getBean(PedidoService.class);
		FacturaService facturaService = contexto.getBean(FacturaService.class);

		System.out.println("Contexto de Spring iniciado con " + contexto.getBeanDefinitionNames().length
				+ " beans.");

		Scanner scanner = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
			System.out.println();
			System.out.println("===== CAFETERÍA LA ANTIGUA =====");
			System.out.println("1. Ver menú de productos");
			System.out.println("2. Crear pedido");
			System.out.println("3. Ver resumen de ventas");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");

			int opcion = leerEntero(scanner);
			switch (opcion) {
				case 1:
					mostrarMenuProductos(productoRepository);
					break;
				case 2:
					crearPedido(scanner, productoRepository, pedidoService, facturaService);
					break;
				case 3:
					mostrarResumenVentas(pedidoService);
					break;
				case 4:
					salir = true;
					break;
				default:
					System.out.println("Opción no válida, intente de nuevo.");
			}
		}

		System.out.println("Gracias por usar Cafetería La Antigua. ¡Hasta pronto!");
	}

	private void mostrarMenuProductos(ProductoRepository productoRepository) {
		System.out.println();
		System.out.printf("%-5s %-30s %10s%n", "Código", "Producto", "Precio");
		System.out.println("----------------------------------------");
		for (Producto producto : productoRepository.listarProductos()) {
			System.out.printf("%-5s %-30s %10s%n", producto.getCodigo(), producto.getNombre(),
					String.format("Q %.2f", producto.getPrecio()));
		}
	}

	private void crearPedido(Scanner scanner, ProductoRepository productoRepository,
			PedidoService pedidoService, FacturaService facturaService) {
		Pedido pedido = pedidoService.crearPedido();
		System.out.println();
		System.out.println("Creando " + pedido.getNumeroPedido() + ". Ingrese el código del producto,");
		System.out.println("'F' para finalizar el pedido o 'C' para cancelarlo.");

		boolean pedidoActivo = true;
		while (pedidoActivo) {
			System.out.print("Código del producto: ");
			String codigo = scanner.nextLine().trim();

			if (codigo.equalsIgnoreCase("F")) {
				if (pedidoService.finalizarPedido(pedido)) {
					facturaService.imprimirFactura(pedido);
				} else {
					System.out.println("El pedido está vacío, se cancela sin factura.");
				}
				pedidoActivo = false;
			} else if (codigo.equalsIgnoreCase("C")) {
				System.out.println("Pedido cancelado.");
				pedidoActivo = false;
			} else if (codigo.isEmpty()) {
				System.out.println("Debe ingresar un código de producto.");
			} else if (productoRepository.buscarPorCodigo(codigo) == null) {
				System.out.println("El producto con código " + codigo + " no existe.");
			} else {
				System.out.print("Cantidad: ");
				int cantidad = leerEntero(scanner);
				if (cantidad <= 0) {
					System.out.println("La cantidad debe ser mayor a cero.");
				} else {
					String error = pedidoService.agregarProducto(pedido, codigo, cantidad);
					if (error != null) {
						System.out.println(error);
					} else {
						System.out.println(cantidad + " x " + codigo + " agregado al pedido.");
					}
				}
			}
		}
	}

	private void mostrarResumenVentas(PedidoService pedidoService) {
		System.out.println();
		System.out.println("===== RESUMEN DE VENTAS =====");
		System.out.println("Pedidos realizados: " + pedidoService.getCantidadPedidos());
		System.out.printf("Total vendido: Q %.2f%n", pedidoService.getTotalVendido());
	}

	private int leerEntero(Scanner scanner) {
		String entrada = scanner.nextLine().trim();
		try {
			return Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
