package mx.com.ironbit.msoempleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.ironbit.msoempleado.entity.EmpleadoEntity;


/**
 * Interfaz EmpleadoDAO
 * @author Eduardo Guillen Maldonado
 * 
 */
@Repository
public interface EmpleadoDAO extends CrudRepository<EmpleadoEntity, Integer>, JpaRepository<EmpleadoEntity, Integer> {

	@Query(" SELECT e FROM EmpleadoEntity e where e.primerNombre=:primerNombre "
			+ " and e.apellidoPaterno=:apellidoPaterno " + " and e.apellidoMaterno=:apellidoMaterno "
			+ " and e.edad=:edad")
	public EmpleadoEntity findEmpleado(
			@Param("primerNombre") String primerNombre,
			@Param("apellidoPaterno") String apellidoPaterno, 
			@Param("apellidoMaterno") String apellidoMaterno,
			@Param("edad") Integer edad);

}
