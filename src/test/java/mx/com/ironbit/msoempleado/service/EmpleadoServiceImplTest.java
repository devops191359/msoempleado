package mx.com.ironbit.msoempleado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import mx.com.ironbit.msoempleado.common.Util;
import mx.com.ironbit.msoempleado.dao.EmpleadoDAO;
import mx.com.ironbit.msoempleado.entity.EmpleadoEntity;
import mx.com.ironbit.msoempleado.request.EmpleadoModelReq;
import mx.com.ironbit.msoempleado.request.EmpleadosRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmpleadoServiceImplTest {

	@Mock
	private EmpleadoDAO empleadoDAO;
	@Mock
	private Util util;

	@InjectMocks
	private EmpleadoServiceImpl empleadoService;

	private EmpleadoEntity emp;

	private EmpleadosRequest empleadoRequest;

	private List<EmpleadoModelReq> empleados;

	private EmpleadoModelReq empModel;

	@BeforeEach
	public void init() {
		// REQUEST
		empleadoRequest = new EmpleadosRequest();
		empleados = new ArrayList<>();
		empModel = new EmpleadoModelReq();
		empModel.setPrimerNombre("Eduardo");
		empModel.setSegundoNombre("");
		empModel.setApellidoPaterno("Guillen");
		empModel.setApellidoMaterno("Maldonado");
		empModel.setEdad(30);
		empModel.setSexo("M");
		empModel.setFechaNacimiento("02/04/1994");
		empModel.setPuesto("Desarrollador Java");
		empleados.add(empModel);
		empleadoRequest.setEmpleados(empleados);

		// ENTIDAD
		emp = new EmpleadoEntity();
		emp.setPrimerNombre("Eduardo");
		emp.setSegundoNombre("");
		emp.setApellidoPaterno("Guillen");
		emp.setApellidoMaterno("Maldonado");
		emp.setEdad(30);
		emp.setSexo("M");
		emp.setFechaNacimiento("02/04/1994");
		emp.setPuesto("Desarrollador Java");
	}

	@Test
	void testCreateEmpleado() {
		Mockito.when(empleadoDAO.findById(1)).thenReturn(Optional.of(emp));
		Assertions.assertThrows(Exception.class, () -> {
			Object response = empleadoService.setEmpleados(empleadoRequest);

			Assertions.assertNull(response);
			Mockito.verify(empleadoDAO, Mockito.times(1)).findById(ArgumentMatchers.any());
			Mockito.verifyNoMoreInteractions(empleadoDAO);
		});

	}
}
