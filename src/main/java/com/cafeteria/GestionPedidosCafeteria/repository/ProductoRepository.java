package com.cafeteria.GestionPedidosCafeteria.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cafeteria.GestionPedidosCafeteria.model.Producto;

@Component
public class ProductoRepository {

	private final List<Producto> productos;

	public ProductoRepository() {
		this.productos = new ArrayList<>();
		productos.add(new Producto("C01", "Café de Huehuetenango", 18.00));
		productos.add(new Producto("C02", "Café de Antigua (Americano)", 15.00));
		productos.add(new Producto("C03", "Par de Champurradas", 10.00));
		productos.add(new Producto("C04", "Rellenitos de Plátano", 12.00));
		productos.add(new Producto("C05", "Chuchito Tradicional", 10.00));
		productos.add(new Producto("C06", "Atol de Elote", 14.00));
		productos.add(new Producto("C07", "Molletes (2 unidades)", 20.00));
	}

	public List<Producto> listarProductos() {
		return productos;
	}

	public Producto buscarPorCodigo(String codigo) {
		for (Producto producto : productos) {
			if (producto.getCodigo().equalsIgnoreCase(codigo)) {
				return producto;
			}
		}
		return null;
	}
}
