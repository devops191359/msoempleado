package mx.com.ironbit.msoempleado.service;

import mx.com.ironbit.msoempleado.request.EmpleadoModelReqUpd;
import mx.com.ironbit.msoempleado.request.EmpleadosRequest;


/**
 * Interfaz de Servicio  EmpleadoService
 * @author Eduardo Guillen Maldonado
 * 
 */
public interface EmpleadoService {

	public Object getEmpleados();

	public Object setEmpleados(EmpleadosRequest empleadosRequest);

	public Object deleteEmpleado(Integer id);

	public Object setEmpleado(EmpleadoModelReqUpd req);

}
