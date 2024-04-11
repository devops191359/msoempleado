package mx.com.ironbit.msoempleado.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import mx.com.ironbit.msoempleado.common.Constantes;
import mx.com.ironbit.msoempleado.common.Util;
import mx.com.ironbit.msoempleado.dao.EmpleadoDAO;
import mx.com.ironbit.msoempleado.entity.EmpleadoEntity;
import mx.com.ironbit.msoempleado.request.EmpleadoModelReq;
import mx.com.ironbit.msoempleado.request.EmpleadoModelReqUpd;
import mx.com.ironbit.msoempleado.request.EmpleadosRequest;
import mx.com.ironbit.msoempleado.response.RespuestaGenericResponse;
import mx.com.ironbit.msoempleado.util.model.SEExceptionAPI;



/**
 * Clase Implementación de Servicio EmpleadoServiceImpl
 * @author Eduardo Guillen Maldonado
 * 
 */
@RequiredArgsConstructor
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	private final EmpleadoDAO empleadoDAO;

	private final Util util;

	@Transactional
	@Override
	public Object setEmpleados(EmpleadosRequest empleadosRequest) {

		if (empleadosRequest.getEmpleados().isEmpty()) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("No se ha ingresado un empleado, favor de validar"));
		}

		int maxSize = empleadosRequest.getEmpleados().size();

		List<EmpleadoModelReq> emps = empleadosRequest.getEmpleados();

		for (int i = 0; i < maxSize; i++) {

			EmpleadoModelReq emp = (EmpleadoModelReq) emps.get(i);

			if (emp.getPrimerNombre().isEmpty() || emp.getPrimerNombre().equals("")) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i + "].primerNombre esta vacio"));
			}
			if (emp.getApellidoPaterno().isEmpty() || emp.getApellidoPaterno().equals("")) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i + "].apellidoPaterno esta vacio"));
			}

			if (emp.getEdad() <= 0) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i + "].edad debe ser mayor a cero"));
			}

			if (emp.getSexo().isEmpty() || emp.getSexo().equals("")) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i + "].sexo esta vacio"));
			}

			if (emp.getFechaNacimiento().isEmpty() || emp.getFechaNacimiento().equals("")) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i + "].fechaNacimiento esta vacio"));
			}

			if (!emp.getFechaNacimiento().matches("^[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}$")) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i
								+ "].fechaNacimiento es invalido, ingrese el formato dd/mm/yyyy"));
			}

			if (emp.getPuesto().isEmpty() || emp.getPuesto().equals("")) {
				throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
						util.getFolio(), Arrays.asList("El campo empleados[" + i + "].puesto esta vacio"));
			}
		}

		List<EmpleadoEntity> empleados = new ArrayList<>();
		List<EmpleadoEntity> empleados2 = new ArrayList<>();
		empleadosRequest.getEmpleados().parallelStream().forEach(e -> {
			EmpleadoEntity emp = new EmpleadoEntity();
			emp.setPrimerNombre(e.getPrimerNombre());
			emp.setSegundoNombre(e.getSegundoNombre());
			emp.setApellidoPaterno(e.getApellidoPaterno());
			emp.setApellidoMaterno(e.getApellidoMaterno());
			emp.setEdad(e.getEdad());
			emp.setSexo(e.getSexo());
			emp.setFechaNacimiento(e.getFechaNacimiento());
			emp.setPuesto(e.getPuesto());

			EmpleadoEntity empFound = empleadoDAO.findEmpleado(e.getPrimerNombre(), e.getApellidoPaterno(),
					e.getApellidoMaterno(), e.getEdad());

			if (empFound == null) {
				if (!empleados.contains(emp)) {
					empleados.add(emp);
				}
			}
		});

		empleados2 = empleadoDAO.saveAll(empleados);

		if (empleados2.isEmpty()) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList(
							"No se realizo ningun registro en la BD debido a que la informacion ingresa ya existe"));
		}

		return empleados2;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	@Override
	public Object getEmpleados() {

		List<EmpleadoEntity> empleados = empleadoDAO.findAll();

		if (empleados.isEmpty()) {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("No se encontro información de los empleados"));
		}

		return empleados;
	}

	@Override
	public Object deleteEmpleado(Integer id) {

		if (id.intValue() < 0) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo id debe ser mayor a cero"));
		}

		if (empleadoDAO.existsById(id)) {
			RespuestaGenericResponse rg = new RespuestaGenericResponse();
			empleadoDAO.deleteById(id);
			rg.setStatus(true);
			rg.setDescripcion("El empleado con el id " + id + " ha sido eliminado");
			return rg;
		} else {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("El empleado que intenta eliminar no existe"));
		}

	}

	@Override
	public Object setEmpleado(EmpleadoModelReqUpd req) {

		if (req.getId().intValue() < 0) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo id debe ser mayor a cero"));
		}

		if (req.getPrimerNombre().isEmpty() || req.getPrimerNombre().equals("")) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo primerNombre esta vacio"));
		}
		if (req.getApellidoPaterno().isEmpty() || req.getApellidoPaterno().equals("")) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo apellidoPaterno esta vacio"));
		}

		if (req.getEdad() <= 0) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo edad debe ser mayor a cero"));
		}

		if (req.getSexo().isEmpty() || req.getSexo().equals("")) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo sexo esta vacio"));
		}

		if (req.getFechaNacimiento().isEmpty() || req.getFechaNacimiento().equals("")) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo fechaNacimiento esta vacio"));
		}

		if (!req.getFechaNacimiento().matches("^[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}$")) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(),
					Arrays.asList("El campo fechaNacimiento es invalido, ingrese el formato dd/mm/yyyy"));
		}

		if (req.getPuesto().isEmpty() || req.getPuesto().equals("")) {
			throw new SEExceptionAPI(HttpStatus.BAD_REQUEST, util.getCodigo(), Constantes.OPERACION_400,
					util.getFolio(), Arrays.asList("El campo puesto esta vacio"));
		}

		if (empleadoDAO.existsById(req.getId())) {

			EmpleadoEntity emp = new EmpleadoEntity();
			emp.setId(req.getId());
			emp.setPrimerNombre(req.getPrimerNombre());
			emp.setSegundoNombre(req.getSegundoNombre());
			emp.setApellidoPaterno(req.getApellidoPaterno());
			emp.setApellidoMaterno(req.getApellidoMaterno());
			emp.setEdad(req.getEdad());
			emp.setSexo(req.getSexo());
			emp.setFechaNacimiento(req.getFechaNacimiento());
			emp.setPuesto(req.getPuesto());
			emp = empleadoDAO.saveAndFlush(emp);
			RespuestaGenericResponse rg = new RespuestaGenericResponse();
			rg.setStatus(true);
			rg.setDescripcion("El empleado con el id " + req.getId() + " ha sido modificado");

			return rg;
		} else {
			throw new SEExceptionAPI(HttpStatus.NOT_FOUND, util.getCodigo(), Constantes.OPERACION_404, util.getFolio(),
					Arrays.asList("El empleado que intenta modificar no existe"));
		}

	}

}
