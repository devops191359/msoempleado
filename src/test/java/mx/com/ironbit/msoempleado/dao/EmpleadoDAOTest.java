package mx.com.ironbit.msoempleado.dao;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;
import mx.com.ironbit.msoempleado.entity.EmpleadoEntity;

@Slf4j
@DataJpaTest
public class EmpleadoDAOTest {

	@Autowired
	private EmpleadoDAO empleadoDAO;

	@Test
	void testCreate() {
		EmpleadoEntity emp = new EmpleadoEntity();
		emp.setPrimerNombre("Eduardo");
		emp.setSegundoNombre("");
		emp.setApellidoPaterno("Guillen");
		emp.setApellidoMaterno("Maldonado");
		emp.setEdad(30);
		emp.setSexo("M");
		emp.setFechaNacimiento("02/04/1994");
		emp.setPuesto("Desarrollador Java");

		emp = this.empleadoDAO.saveAndFlush(emp);

		if (emp != null) {
			log.info("se registro el empleado {}", emp);
		}
		Assert.assertNotNull(emp);

	}

	@Test
	void testFindAll() {
		List<EmpleadoEntity> empleados = empleadoDAO.findAll();
		if (!empleados.isEmpty()) {
			log.info("si hay empleados {}", empleados);
		}
		Assert.assertNotNull(empleados);
	}

	@Test
	void testFindOne() {
		EmpleadoEntity empleado = empleadoDAO.findById(1).orElse(null);
		assertEquals(null, empleado);
	}

}
