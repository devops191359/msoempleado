package mx.com.ironbit.msoempleado.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase Bean EmpleadoModelReqUpd
 * @author Eduardo Guillen Maldonado
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmpleadoModelReqUpd {

	private Integer id;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Integer edad;
	private String sexo;
	private String fechaNacimiento;
	private String puesto;

}
