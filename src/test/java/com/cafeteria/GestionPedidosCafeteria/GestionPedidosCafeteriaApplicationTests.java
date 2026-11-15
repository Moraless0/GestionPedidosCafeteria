package com.cafeteria.GestionPedidosCafeteria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cafeteria.GestionPedidosCafeteria.runner.CafeteriaRunner;

@SpringBootTest
class GestionPedidosCafeteriaApplicationTests {

	// Se reemplaza el runner para que el menú de consola no se ejecute durante la prueba
	@MockitoBean
	private CafeteriaRunner cafeteriaRunner;

	@Test
	void contextLoads() {
	}

}
