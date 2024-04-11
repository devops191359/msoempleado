package mx.com.ironbit.msoempleado.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




/**
 * Clase Bean RespuestaGenericResponse
 * @author Eduardo Guillen Maldonado
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RespuestaGenericResponse {
	private boolean status;
	private String descripcion;
}
