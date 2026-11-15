package com.cafeteria.GestionPedidosCafeteria.service;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.cafeteria.GestionPedidosCafeteria.model.DetallePedido;
import com.cafeteria.GestionPedidosCafeteria.model.Pedido;

@Service
public class FacturaService {

	private final CalculadoraService calculadoraService;

	public FacturaService(CalculadoraService calculadoraService) {
		this.calculadoraService = calculadoraService;
	}

	public void imprimirFactura(Pedido pedido) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		double subtotal = calculadoraService.calcularSubtotal(pedido);
		double iva = calculadoraService.calcularIva(pedido);
		double total = calculadoraService.calcularTotal(pedido);

		System.out.println("========================================");
		System.out.println("      CAFETERÍA LA ANTIGUA - GUATEMALA");
		System.out.println("========================================");
		System.out.printf("Pedido: %-10s Fecha: %s%n", pedido.getNumeroPedido(),
				pedido.getFechaHora().format(formato));
		System.out.println("----------------------------------------");
		System.out.printf("%-28s %5s %10s %12s%n", "Producto", "Cant.", "P.Unit", "Subtotal");
		for (DetallePedido detalle : pedido.getDetalles()) {
			System.out.printf("%-28s %5d %10s %12s%n",
					detalle.getProducto().getNombre(),
					detalle.getCantidad(),
					String.format("Q %.2f", detalle.getProducto().getPrecio()),
					String.format("Q %.2f", detalle.getSubtotal()));
		}
		System.out.println("----------------------------------------");
		System.out.printf("%-33s Q %.2f%n", "Subtotal:", subtotal);
		System.out.printf("%-33s Q %.2f%n", "IVA (12%):", iva);
		System.out.printf("%-33s Q %.2f%n", "TOTAL A PAGAR:", total);
		System.out.println("========================================");
	}
}
