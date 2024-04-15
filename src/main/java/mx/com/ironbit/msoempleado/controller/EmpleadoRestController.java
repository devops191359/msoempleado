package mx.com.ironbit.msoempleado.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.ironbit.msoempleado.common.Constantes;
import mx.com.ironbit.msoempleado.common.Util;
import mx.com.ironbit.msoempleado.request.EmpleadoModelReqUpd;
import mx.com.ironbit.msoempleado.request.EmpleadosRequest;
import mx.com.ironbit.msoempleado.service.BitacoraService;
import mx.com.ironbit.msoempleado.service.EmpleadoService;
import mx.com.ironbit.msoempleado.util.model.ResponseService;
import mx.com.ironbit.msoempleado.util.model.SEExceptionAPI;
import mx.com.ironbit.msoempleado.util.model.TrazaModel;

/**
 * Clase Rest Controller EmpleadoRestController
 * 
 * @author Eduardo Guillen Maldonado
 * 
 */
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Empleados", description = "Proceso CRUD Empleados")
@RestController
@RequestMapping("/empleados")
public class EmpleadoRestController {

	private final EmpleadoService service;

	private final Util util;

	private final HttpServletRequest requestP;

	private final BitacoraService bitacoraService;

	/**
	 * Método que devuelve los empleados
	 * 
	 * @exception SEExceptionAPI Se genera una excepción genérica.
	 * @return object
	 */

	@Operation(summary = "Obtiene todos los empleados", description = "Realiza una busqueda general de todos los empleados", tags = {
			"Empleados" })
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación Exitosa", content = {}),
			@ApiResponse(responseCode = "404", description = "Información no encontrada, favor de validar", content = {}),
			@ApiResponse(responseCode = "401", description = "No autorizado, favor de validar", content = {}),
			@ApiResponse(responseCode = "403", description = "Violación de seguridad, favor de validar", content = {}),
			@ApiResponse(responseCode = "500", description = "Problema interno en el servidor, favor de validar", content = {}) })
	@GetMapping("/busquedas")
	public Object getEmpleados() {

		String ipAddress = requestP.getHeader(Constantes.HEADER_X_FORWARDED);
		if (ipAddress == null) {
			ipAddress = requestP.getRemoteAddr();
		}
		this.saveTraza(ipAddress, "", Constantes.METHOD_GET_EMPLEADOS);

		try {
			return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(),
					service.getEmpleados());
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}

	/**
	 * Método que elimina los empleados
	 * 
	 * @exception SEExceptionAPI Se genera una excepción genérica.
	 * @return object
	 */

	@Operation(summary = "Elimina a un empleado", description = "Realiza la eliminación de un empleado existente", tags = {
			"Empleados" })
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Operación Exitosa", content = {}),
			@ApiResponse(responseCode = "404", description = "Información no encontrada, favor de validar", content = {}),
			@ApiResponse(responseCode = "401", description = "No autorizado, favor de validar", content = {}),
			@ApiResponse(responseCode = "403", description = "Violación de seguridad, favor de validar", content = {}),
			@ApiResponse(responseCode = "500", description = "Problema interno en el servidor, favor de validar", content = {}) })
	@DeleteMapping("/{id}")
	public Object deleteEmpleadoById(@PathVariable(name = "id", required = true) Integer id) {
		String ipAddress = requestP.getHeader(Constantes.HEADER_X_FORWARDED);
		if (ipAddress == null) {
			ipAddress = requestP.getRemoteAddr();
		}
		this.saveTraza(ipAddress, String.valueOf(id), Constantes.METHOD_DELETE_EMPLEADO);

		try {
			return new ResponseService(HttpStatus.NO_CONTENT, util.getCodigo(), Constantes.OPERACION_204,
					util.getFolio(), service.deleteEmpleado(id));
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}

	/**
	 * Método que da de alta a uno o varios empleados
	 * 
	 * @exception SEExceptionAPI Se genera una excepción genérica.
	 * @return object
	 */
	@Operation(summary = "Alta de empleado(s)", description = "Se realiza el alta de uno o varios empleados", tags = {
			"Empleados" })
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Operación Exitosa", content = {}),
			@ApiResponse(responseCode = "404", description = "Información no encontrada, favor de validar", content = {}),
			@ApiResponse(responseCode = "401", description = "No autorizado, favor de validar", content = {}),
			@ApiResponse(responseCode = "403", description = "Violación de seguridad, favor de validar", content = {}),
			@ApiResponse(responseCode = "500", description = "Problema interno en el servidor, favor de validar", content = {}) })
	@PostMapping
	public Object setEmpleados(@RequestBody @Valid EmpleadosRequest empleadosRequest) {

		String ipAddress = requestP.getHeader(Constantes.HEADER_X_FORWARDED);
		if (ipAddress == null) {
			ipAddress = requestP.getRemoteAddr();
		}
		this.saveTraza(ipAddress, empleadosRequest, Constantes.METHOD_SET_EMPLEADOS);
		try {
			return new ResponseService(HttpStatus.CREATED, util.getCodigo(), Constantes.OPERACION_201, util.getFolio(),
					service.setEmpleados(empleadosRequest));
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}

	/**
	 * Método que actualiza la información del empleado
	 * 
	 * @exception SEExceptionAPI Se genera una excepción genérica.
	 * @return object
	 */
	@Operation(summary = "Actualización de datos del empleado", description = "Se realiza la actualización de los datos de un empleado existente", tags = {
			"Empleados" })
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Operación Exitosa", content = {}),
			@ApiResponse(responseCode = "404", description = "Información no encontrada, favor de validar", content = {}),
			@ApiResponse(responseCode = "401", description = "No autorizado, favor de validar", content = {}),
			@ApiResponse(responseCode = "403", description = "Violación de seguridad, favor de validar", content = {}),
			@ApiResponse(responseCode = "500", description = "Problema interno en el servidor, favor de validar", content = {}) })
	@PutMapping
	public Object setEmpleado(@RequestBody @Valid EmpleadoModelReqUpd empleadosRequest) {

		String ipAddress = requestP.getHeader(Constantes.HEADER_X_FORWARDED);
		if (ipAddress == null) {
			ipAddress = requestP.getRemoteAddr();
		}
		this.saveTraza(ipAddress, empleadosRequest, Constantes.METHOD_SET_EMPLEADO);
		try {
			return new ResponseService(HttpStatus.CREATED, util.getCodigo(), Constantes.OPERACION_201, util.getFolio(),
					service.setEmpleado(empleadosRequest));
		} catch (SEExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			SEExceptionAPI ex = new SEExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.getCodigo(),
					Constantes.OPERACION_500, util.getFolio(), Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}

	private void saveTraza(String ipAddress, Object request, String metodo) {
		TrazaModel tr = new TrazaModel();
		tr.setRequestBody(request);
		tr.setIpOrigen(ipAddress);
		tr.setMetodo(metodo);
		tr.setFechaTransaccion(new SimpleDateFormat(Constantes.FORMAT_DATE_STR).format(new Date()));
		log.info("log traza {}",tr);
		this.bitacoraService.saveTransaccion(tr);
	}

}
