package com.cafeteria.GestionPedidosCafeteria.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cafeteria.GestionPedidosCafeteria.model.DetallePedido;
import com.cafeteria.GestionPedidosCafeteria.model.Pedido;
import com.cafeteria.GestionPedidosCafeteria.model.Producto;
import com.cafeteria.GestionPedidosCafeteria.repository.ProductoRepository;

@Service
public class PedidoService {

	private final ProductoRepository productoRepository;
	private final CalculadoraService calculadoraService;

	private int contadorPedidos = 0;
	private int cantidadPedidos = 0;
	private double totalVendido = 0.0;

	public PedidoService(ProductoRepository productoRepository, CalculadoraService calculadoraService) {
		this.productoRepository = productoRepository;
		this.calculadoraService = calculadoraService;
	}

	public Pedido crearPedido() {
		contadorPedidos++;
		String numero = String.format("PED-%03d", contadorPedidos);
		return new Pedido(numero, LocalDateTime.now());
	}

	public String agregarProducto(Pedido pedido, String codigo, int cantidad) {
		Producto producto = productoRepository.buscarPorCodigo(codigo);
		if (producto == null) {
			return "El producto con código " + codigo + " no existe.";
		}
		if (cantidad <= 0) {
			return "La cantidad debe ser mayor a cero.";
		}
		pedido.agregarDetalle(new DetallePedido(producto, cantidad));
		return null;
	}

	public boolean finalizarPedido(Pedido pedido) {
		if (pedido.isVacio()) {
			return false;
		}
		cantidadPedidos++;
		totalVendido += calculadoraService.calcularTotal(pedido);
		return true;
	}

	public int getCantidadPedidos() {
		return cantidadPedidos;
	}

	public double getTotalVendido() {
		return totalVendido;
	}
}
