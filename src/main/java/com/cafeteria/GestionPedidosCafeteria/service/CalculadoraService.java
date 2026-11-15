package com.cafeteria.GestionPedidosCafeteria.service;

import org.springframework.stereotype.Service;

import com.cafeteria.GestionPedidosCafeteria.model.DetallePedido;
import com.cafeteria.GestionPedidosCafeteria.model.Pedido;

@Service
public class CalculadoraService {

	// Tasa del Impuesto al Valor Agregado en Guatemala
	private static final double PORCENTAJE_IVA = 0.12;

	public CalculadoraService() {
	}

	private double redondear(double valor) {
		return Math.round(valor * 100.0) / 100.0;
	}

	public double calcularSubtotalDetalle(DetallePedido detalle) {
		return detalle.getSubtotal();
	}

	public double calcularSubtotal(Pedido pedido) {
		double subtotal = 0.0;
		for (DetallePedido detalle : pedido.getDetalles()) {
			subtotal += detalle.getSubtotal();
		}
		return redondear(subtotal);
	}

	public double calcularIva(Pedido pedido) {
		return redondear(calcularSubtotal(pedido) * PORCENTAJE_IVA);
	}

	public double calcularTotal(Pedido pedido) {
		return redondear(calcularSubtotal(pedido) + calcularIva(pedido));
	}
}
