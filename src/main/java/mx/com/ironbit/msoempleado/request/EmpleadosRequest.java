package mx.com.ironbit.msoempleado.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase Bean EmpleadosRequest
 * @author Eduardo Guillen Maldonado
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmpleadosRequest {
	
	private List<EmpleadoModelReq> empleados;

}
