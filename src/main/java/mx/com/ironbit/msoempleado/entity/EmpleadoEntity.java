package mx.com.ironbit.msoempleado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase Entidad EmpleadoEntity
 * @author Eduardo Guillen Maldonado
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "TA_EMPLEADO")
public class EmpleadoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FIID", unique = true)
	private int id;

	@Column(name = "FCPRIMERNOMBRE", nullable = false, length = 50)
	private String primerNombre;

	@Column(name = "FCSEGNOMBRE", nullable = true)
	private String segundoNombre;

	@Column(name = "FCAPPATERNO", nullable = false, length = 50)
	private String apellidoPaterno;

	@Column(name = "FCAPMATERNO", nullable = true)
	private String apellidoMaterno;

	@Column(name = "FIEDAD", nullable = false)
	private Integer edad;

	@Column(name = "FCSEXO", nullable = false, length = 2)
	private String sexo;

	@Column(name = "FDFECHANAC")
	private String fechaNacimiento;

	@Column(name = "FCPUESTODESC", nullable = false, length = 60)
	private String puesto;

}
