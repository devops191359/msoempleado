package mx.com.ironbit.msoempleado.common;


/**
 * Clase para manejo de  Constantes
 * @author Eduardo Guillen Maldonado
 * 
 */
public final class Constantes {

	public static final Integer MS_ID=432;
	public static final String NOMBRE_MSD = "demo";
	public static final String NOMBRE_MSDRT = "demo";

	public static final int PREFIX_400 = 400;
	public static final int PREFIX_401 = 401;
	public static final int PREFIX_500 = 500;
	public static final String PREFIX_CODE_400 = "400";
	public static final Integer PREFIX_CODE_404 = 404;

	// CODIGOS DE MENSAJE CABECERO
	public static final String OPERACION_200 = "Operación Exitosa";
	public static final String OPERACION_201 = "Operación Exitosa";
	public static final String OPERACION_204 = "Operación Exitosa";
	public static final String OPERACION_400 = "Solicitud mal formada, favor de validar";
	public static final String OPERACION_401 = "No autorizado, favor de validar";
	public static final String OPERACION_404 = "Información no encontrada, favor de validar";
	public static final String OPERACION_500 = "Problema interno en el servidor, favor de validar";

	public static final String OPERACION_400_DETAIL = "Los datos de entrada son incorrectos, favor de validar";
	
	
	//METODOS CONSTANT
	public static final String METHOD_GET_EMPLEADOS = "getEmpleados()";
	public static final String METHOD_DELETE_EMPLEADO = "deleteEmpleadoById()";
	public static final String METHOD_SET_EMPLEADOS = "setEmpleados()";
	public static final String METHOD_SET_EMPLEADO = "setEmpleado()";

	
	public static final String FORMAT_DATE_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String HEADER_X_FORWARDED="X-FORWARDED-FOR";


}