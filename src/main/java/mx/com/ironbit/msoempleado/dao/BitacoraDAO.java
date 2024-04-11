package mx.com.ironbit.msoempleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.ironbit.msoempleado.entity.BitacoraEntity;


@Repository
public interface BitacoraDAO extends CrudRepository<BitacoraEntity, Integer>, JpaRepository<BitacoraEntity, Integer> {

}
