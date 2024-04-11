package mx.com.ironbit.msoempleado.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import mx.com.ironbit.msoempleado.common.Util;
import mx.com.ironbit.msoempleado.service.BitacoraService;
import mx.com.ironbit.msoempleado.service.EmpleadoService;

@WebMvcTest(EmpleadoRestController.class)
public class EmpleadoRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmpleadoService empleadoService;

	@MockBean
	private Util util;

	@MockBean
	private BitacoraService bitacoraService;

	//@MockBean
	//private HttpServletRequest requestP;

	@Test
	public void testVerDetalles() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/empleados/busquedas").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().is5xxServerError())
				.andExpect(content()
						.json("{\"codigo\": null,\"mensaje\":\"Problema interno en el servidor, favor de validar\"}"))
				.andReturn();

	}
}