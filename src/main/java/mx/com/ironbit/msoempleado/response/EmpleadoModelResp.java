package mx.com.ironbit.msoempleado.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 * Clase Bean EmpleadoModelResp
 * @author Eduardo Guillen Maldonado
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmpleadoModelResp {

	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Integer edad;
	private String sexo;
	private String fechaNacimiento;
	private String puesto;

}
