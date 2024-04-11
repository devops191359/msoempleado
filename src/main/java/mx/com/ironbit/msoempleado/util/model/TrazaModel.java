package mx.com.ironbit.msoempleado.util.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TrazaModel implements Serializable {

	private String ipOrigen;

	private String fechaTransaccion;

	private String metodo;

	private Object requestBody;

}
