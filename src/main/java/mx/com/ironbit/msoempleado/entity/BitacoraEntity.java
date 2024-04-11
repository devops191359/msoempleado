package mx.com.ironbit.msoempleado.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad BitacoraEntity
 * @author Eduardo Guillen Maldonado
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TA_BITACORA")
public class BitacoraEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FIIDTRAN", unique = true)
	private Integer idTransaccion;

	@Column(name = "FIIPORIGEN", nullable = false, length = 70)
	private String ipOrigen;

	@Column(name = "FCFECHAREQ", nullable = false, length = 60)
	private String fechaRequest;

	@Column(name = "FCMETODOEJ", nullable = false, length = 90)
	private String metodoEjecucion;
	
	
	@Column(name = "FCTRAZA", nullable = false, length = 900)
	private String trazaJson;

}
