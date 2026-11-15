package com.cafeteria.GestionPedidosCafeteria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private String numeroPedido;
	private LocalDateTime fechaHora;
	private List<DetallePedido> detalles = new ArrayList<>();

	public Pedido(String numeroPedido, LocalDateTime fechaHora) {
		this.numeroPedido = numeroPedido;
		this.fechaHora = fechaHora;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void agregarDetalle(DetallePedido detalle) {
		detalles.add(detalle);
	}

	public boolean isVacio() {
		return detalles.isEmpty();
	}
}
