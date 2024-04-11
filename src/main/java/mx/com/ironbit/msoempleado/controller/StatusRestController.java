package mx.com.ironbit.msoempleado.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.ironbit.msoempleado.common.Constantes;
import mx.com.ironbit.msoempleado.common.Util;
import mx.com.ironbit.msoempleado.util.model.ResponseService;


/**
 * Clase Rest Controller StatusRestController
 * @author  Eduardo Guillen Maldonado
 * 
 */
@Tag(name = "Healthcheck", description = "Proceso Salud Microservicio")
@RestController
@RequestMapping("/status")
public class StatusRestController {

	@Autowired
	private Util util;

	@Value("${info.app.name}")
	private String nombreAplicacion;
	
	@Value("${info.app.version}")
	private String appVersion;


	/**
     *  Método que devuelve el estatus de salud de vida del microservicio
     *  @return object
     *  
     */
	@Operation(summary = "Obtiene el estatus actual del microservicio", description = "Obtiene la salud del microservicio", tags = {
	"Healthcheck" })
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operación Exitosa", content = {}),
		@ApiResponse(responseCode = "404", description = "Información no encontrada, favor de validar", content = {}) ,
		@ApiResponse(responseCode = "401", description = "No autorizado, favor de validar", content = {}),
		@ApiResponse(responseCode = "403", description = "Violación de seguridad, favor de validar", content = {}),
		@ApiResponse(responseCode = "500", description = "Problema interno en el servidor, favor de validar", content = {})})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseService getStatus() {
		Map<String, String> status = Map.of("aplicacion", nombreAplicacion, "appVersion", appVersion);
		return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.OPERACION_200, util.getFolio(),
				status);
	}


}